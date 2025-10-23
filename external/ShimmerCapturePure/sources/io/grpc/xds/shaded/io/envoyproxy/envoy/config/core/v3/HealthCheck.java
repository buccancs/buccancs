package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.BoolValue;
import com.google.protobuf.BoolValueOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.EventServiceConfig;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HeaderValueOption;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcherOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.CodecClientType;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int64Range;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.Int64RangeOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bouncycastle.crypto.tls.CipherSuite;

/* loaded from: classes6.dex */
public final class HealthCheck extends GeneratedMessageV3 implements HealthCheckOrBuilder {
    public static final int ALT_PORT_FIELD_NUMBER = 6;
    public static final int ALWAYS_LOG_HEALTH_CHECK_FAILURES_FIELD_NUMBER = 19;
    public static final int CUSTOM_HEALTH_CHECK_FIELD_NUMBER = 13;
    public static final int EVENT_LOG_PATH_FIELD_NUMBER = 17;
    public static final int EVENT_SERVICE_FIELD_NUMBER = 22;
    public static final int GRPC_HEALTH_CHECK_FIELD_NUMBER = 11;
    public static final int HEALTHY_EDGE_INTERVAL_FIELD_NUMBER = 16;
    public static final int HEALTHY_THRESHOLD_FIELD_NUMBER = 5;
    public static final int HTTP_HEALTH_CHECK_FIELD_NUMBER = 8;
    public static final int INITIAL_JITTER_FIELD_NUMBER = 20;
    public static final int INTERVAL_FIELD_NUMBER = 2;
    public static final int INTERVAL_JITTER_FIELD_NUMBER = 3;
    public static final int INTERVAL_JITTER_PERCENT_FIELD_NUMBER = 18;
    public static final int NO_TRAFFIC_INTERVAL_FIELD_NUMBER = 12;
    public static final int REUSE_CONNECTION_FIELD_NUMBER = 7;
    public static final int TCP_HEALTH_CHECK_FIELD_NUMBER = 9;
    public static final int TIMEOUT_FIELD_NUMBER = 1;
    public static final int TLS_OPTIONS_FIELD_NUMBER = 21;
    public static final int TRANSPORT_SOCKET_MATCH_CRITERIA_FIELD_NUMBER = 23;
    public static final int UNHEALTHY_EDGE_INTERVAL_FIELD_NUMBER = 15;
    public static final int UNHEALTHY_INTERVAL_FIELD_NUMBER = 14;
    public static final int UNHEALTHY_THRESHOLD_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private static final HealthCheck DEFAULT_INSTANCE = new HealthCheck();
    private static final Parser<HealthCheck> PARSER = new AbstractParser<HealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HealthCheck m22860parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HealthCheck(codedInputStream, extensionRegistryLite);
        }
    };
    private UInt32Value altPort_;
    private boolean alwaysLogHealthCheckFailures_;
    private volatile Object eventLogPath_;
    private EventServiceConfig eventService_;
    private int healthCheckerCase_;
    private Object healthChecker_;
    private Duration healthyEdgeInterval_;
    private UInt32Value healthyThreshold_;
    private Duration initialJitter_;
    private int intervalJitterPercent_;
    private Duration intervalJitter_;
    private Duration interval_;
    private byte memoizedIsInitialized;
    private Duration noTrafficInterval_;
    private BoolValue reuseConnection_;
    private Duration timeout_;
    private TlsOptions tlsOptions_;
    private Struct transportSocketMatchCriteria_;
    private Duration unhealthyEdgeInterval_;
    private Duration unhealthyInterval_;
    private UInt32Value unhealthyThreshold_;

    private HealthCheck(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.healthCheckerCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private HealthCheck() {
        this.healthCheckerCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.eventLogPath_ = "";
    }

    private HealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Struct.Builder builder;
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0:
                            z = true;
                        case 10:
                            Duration duration = this.timeout_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.timeout_ = message;
                            if (builder != null) {
                                builder.mergeFrom(message);
                                this.timeout_ = builder.buildPartial();
                            }
                        case 18:
                            Duration duration2 = this.interval_;
                            builder = duration2 != null ? duration2.toBuilder() : null;
                            Duration message2 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.interval_ = message2;
                            if (builder != null) {
                                builder.mergeFrom(message2);
                                this.interval_ = builder.buildPartial();
                            }
                        case 26:
                            Duration duration3 = this.intervalJitter_;
                            builder = duration3 != null ? duration3.toBuilder() : null;
                            Duration message3 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.intervalJitter_ = message3;
                            if (builder != null) {
                                builder.mergeFrom(message3);
                                this.intervalJitter_ = builder.buildPartial();
                            }
                        case 34:
                            UInt32Value uInt32Value = this.unhealthyThreshold_;
                            builder = uInt32Value != null ? uInt32Value.toBuilder() : null;
                            UInt32Value message4 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.unhealthyThreshold_ = message4;
                            if (builder != null) {
                                builder.mergeFrom(message4);
                                this.unhealthyThreshold_ = builder.buildPartial();
                            }
                        case 42:
                            UInt32Value uInt32Value2 = this.healthyThreshold_;
                            builder = uInt32Value2 != null ? uInt32Value2.toBuilder() : null;
                            UInt32Value message5 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.healthyThreshold_ = message5;
                            if (builder != null) {
                                builder.mergeFrom(message5);
                                this.healthyThreshold_ = builder.buildPartial();
                            }
                        case 50:
                            UInt32Value uInt32Value3 = this.altPort_;
                            builder = uInt32Value3 != null ? uInt32Value3.toBuilder() : null;
                            UInt32Value message6 = codedInputStream.readMessage(UInt32Value.parser(), extensionRegistryLite);
                            this.altPort_ = message6;
                            if (builder != null) {
                                builder.mergeFrom(message6);
                                this.altPort_ = builder.buildPartial();
                            }
                        case 58:
                            BoolValue boolValue = this.reuseConnection_;
                            builder = boolValue != null ? boolValue.toBuilder() : null;
                            BoolValue message7 = codedInputStream.readMessage(BoolValue.parser(), extensionRegistryLite);
                            this.reuseConnection_ = message7;
                            if (builder != null) {
                                builder.mergeFrom(message7);
                                this.reuseConnection_ = builder.buildPartial();
                            }
                        case 66:
                            builder = this.healthCheckerCase_ == 8 ? ((HttpHealthCheck) this.healthChecker_).m22997toBuilder() : null;
                            MessageLite message8 = codedInputStream.readMessage(HttpHealthCheck.parser(), extensionRegistryLite);
                            this.healthChecker_ = message8;
                            if (builder != null) {
                                builder.mergeFrom((HttpHealthCheck) message8);
                                this.healthChecker_ = builder.m23004buildPartial();
                            }
                            this.healthCheckerCase_ = 8;
                        case 74:
                            builder = this.healthCheckerCase_ == 9 ? ((TcpHealthCheck) this.healthChecker_).m23135toBuilder() : null;
                            MessageLite message9 = codedInputStream.readMessage(TcpHealthCheck.parser(), extensionRegistryLite);
                            this.healthChecker_ = message9;
                            if (builder != null) {
                                builder.mergeFrom((TcpHealthCheck) message9);
                                this.healthChecker_ = builder.m23142buildPartial();
                            }
                            this.healthCheckerCase_ = 9;
                        case RESET_TO_DEFAULT_CONFIGURATION_COMMAND_VALUE:
                            builder = this.healthCheckerCase_ == 11 ? ((GrpcHealthCheck) this.healthChecker_).m22950toBuilder() : null;
                            MessageLite message10 = codedInputStream.readMessage(GrpcHealthCheck.parser(), extensionRegistryLite);
                            this.healthChecker_ = message10;
                            if (builder != null) {
                                builder.mergeFrom((GrpcHealthCheck) message10);
                                this.healthChecker_ = builder.m22957buildPartial();
                            }
                            this.healthCheckerCase_ = 11;
                        case 98:
                            Duration duration4 = this.noTrafficInterval_;
                            builder = duration4 != null ? duration4.toBuilder() : null;
                            Duration message11 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.noTrafficInterval_ = message11;
                            if (builder != null) {
                                builder.mergeFrom(message11);
                                this.noTrafficInterval_ = builder.buildPartial();
                            }
                        case 106:
                            builder = this.healthCheckerCase_ == 13 ? ((CustomHealthCheck) this.healthChecker_).m22904toBuilder() : null;
                            MessageLite message12 = codedInputStream.readMessage(CustomHealthCheck.parser(), extensionRegistryLite);
                            this.healthChecker_ = message12;
                            if (builder != null) {
                                builder.mergeFrom((CustomHealthCheck) message12);
                                this.healthChecker_ = builder.m22911buildPartial();
                            }
                            this.healthCheckerCase_ = 13;
                        case 114:
                            Duration duration5 = this.unhealthyInterval_;
                            builder = duration5 != null ? duration5.toBuilder() : null;
                            Duration message13 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.unhealthyInterval_ = message13;
                            if (builder != null) {
                                builder.mergeFrom(message13);
                                this.unhealthyInterval_ = builder.buildPartial();
                            }
                        case 122:
                            Duration duration6 = this.unhealthyEdgeInterval_;
                            builder = duration6 != null ? duration6.toBuilder() : null;
                            Duration message14 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.unhealthyEdgeInterval_ = message14;
                            if (builder != null) {
                                builder.mergeFrom(message14);
                                this.unhealthyEdgeInterval_ = builder.buildPartial();
                            }
                        case 130:
                            Duration duration7 = this.healthyEdgeInterval_;
                            builder = duration7 != null ? duration7.toBuilder() : null;
                            Duration message15 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.healthyEdgeInterval_ = message15;
                            if (builder != null) {
                                builder.mergeFrom(message15);
                                this.healthyEdgeInterval_ = builder.buildPartial();
                            }
                        case 138:
                            this.eventLogPath_ = codedInputStream.readStringRequireUtf8();
                        case 144:
                            this.intervalJitterPercent_ = codedInputStream.readUInt32();
                        case 152:
                            this.alwaysLogHealthCheckFailures_ = codedInputStream.readBool();
                        case 162:
                            Duration duration8 = this.initialJitter_;
                            builder = duration8 != null ? duration8.toBuilder() : null;
                            Duration message16 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.initialJitter_ = message16;
                            if (builder != null) {
                                builder.mergeFrom(message16);
                                this.initialJitter_ = builder.buildPartial();
                            }
                        case 170:
                            TlsOptions tlsOptions = this.tlsOptions_;
                            builder = tlsOptions != null ? tlsOptions.m23182toBuilder() : null;
                            TlsOptions tlsOptions2 = (TlsOptions) codedInputStream.readMessage(TlsOptions.parser(), extensionRegistryLite);
                            this.tlsOptions_ = tlsOptions2;
                            if (builder != null) {
                                builder.mergeFrom(tlsOptions2);
                                this.tlsOptions_ = builder.m23189buildPartial();
                            }
                        case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
                            EventServiceConfig eventServiceConfig = this.eventService_;
                            builder = eventServiceConfig != null ? eventServiceConfig.m21937toBuilder() : null;
                            EventServiceConfig eventServiceConfig2 = (EventServiceConfig) codedInputStream.readMessage(EventServiceConfig.parser(), extensionRegistryLite);
                            this.eventService_ = eventServiceConfig2;
                            if (builder != null) {
                                builder.mergeFrom(eventServiceConfig2);
                                this.eventService_ = builder.m21944buildPartial();
                            }
                        case 186:
                            Struct struct = this.transportSocketMatchCriteria_;
                            builder = struct != null ? struct.toBuilder() : null;
                            Struct message17 = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                            this.transportSocketMatchCriteria_ = message17;
                            if (builder != null) {
                                builder.mergeFrom(message17);
                                this.transportSocketMatchCriteria_ = builder.buildPartial();
                            }
                        default:
                            if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                z = true;
                            }
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

    public static HealthCheck getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HealthCheck> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_descriptor;
    }

    public static HealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(byteBuffer);
    }

    public static HealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(byteString);
    }

    public static HealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(bArr);
    }

    public static HealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HealthCheck parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m22858toBuilder();
    }

    public static Builder newBuilder(HealthCheck healthCheck) {
        return DEFAULT_INSTANCE.m22858toBuilder().mergeFrom(healthCheck);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean getAlwaysLogHealthCheckFailures() {
        return this.alwaysLogHealthCheckFailures_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HealthCheck m22853getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public int getIntervalJitterPercent() {
        return this.intervalJitterPercent_;
    }

    public Parser<HealthCheck> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasAltPort() {
        return this.altPort_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasCustomHealthCheck() {
        return this.healthCheckerCase_ == 13;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasEventService() {
        return this.eventService_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasGrpcHealthCheck() {
        return this.healthCheckerCase_ == 11;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasHealthyEdgeInterval() {
        return this.healthyEdgeInterval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasHealthyThreshold() {
        return this.healthyThreshold_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasHttpHealthCheck() {
        return this.healthCheckerCase_ == 8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasInitialJitter() {
        return this.initialJitter_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasInterval() {
        return this.interval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasIntervalJitter() {
        return this.intervalJitter_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasNoTrafficInterval() {
        return this.noTrafficInterval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasReuseConnection() {
        return this.reuseConnection_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasTcpHealthCheck() {
        return this.healthCheckerCase_ == 9;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasTimeout() {
        return this.timeout_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasTlsOptions() {
        return this.tlsOptions_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasTransportSocketMatchCriteria() {
        return this.transportSocketMatchCriteria_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasUnhealthyEdgeInterval() {
        return this.unhealthyEdgeInterval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasUnhealthyInterval() {
        return this.unhealthyInterval_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public boolean hasUnhealthyThreshold() {
        return this.unhealthyThreshold_ != null;
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
        return new HealthCheck();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(HealthCheck.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public HealthCheckerCase getHealthCheckerCase() {
        return HealthCheckerCase.forNumber(this.healthCheckerCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getTimeout() {
        Duration duration = this.timeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getTimeoutOrBuilder() {
        return getTimeout();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getInterval() {
        Duration duration = this.interval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getIntervalOrBuilder() {
        return getInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getInitialJitter() {
        Duration duration = this.initialJitter_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getInitialJitterOrBuilder() {
        return getInitialJitter();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getIntervalJitter() {
        Duration duration = this.intervalJitter_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getIntervalJitterOrBuilder() {
        return getIntervalJitter();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32Value getUnhealthyThreshold() {
        UInt32Value uInt32Value = this.unhealthyThreshold_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32ValueOrBuilder getUnhealthyThresholdOrBuilder() {
        return getUnhealthyThreshold();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32Value getHealthyThreshold() {
        UInt32Value uInt32Value = this.healthyThreshold_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32ValueOrBuilder getHealthyThresholdOrBuilder() {
        return getHealthyThreshold();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32Value getAltPort() {
        UInt32Value uInt32Value = this.altPort_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public UInt32ValueOrBuilder getAltPortOrBuilder() {
        return getAltPort();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public BoolValue getReuseConnection() {
        BoolValue boolValue = this.reuseConnection_;
        return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public BoolValueOrBuilder getReuseConnectionOrBuilder() {
        return getReuseConnection();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public HttpHealthCheck getHttpHealthCheck() {
        if (this.healthCheckerCase_ == 8) {
            return (HttpHealthCheck) this.healthChecker_;
        }
        return HttpHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public HttpHealthCheckOrBuilder getHttpHealthCheckOrBuilder() {
        if (this.healthCheckerCase_ == 8) {
            return (HttpHealthCheck) this.healthChecker_;
        }
        return HttpHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public TcpHealthCheck getTcpHealthCheck() {
        if (this.healthCheckerCase_ == 9) {
            return (TcpHealthCheck) this.healthChecker_;
        }
        return TcpHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public TcpHealthCheckOrBuilder getTcpHealthCheckOrBuilder() {
        if (this.healthCheckerCase_ == 9) {
            return (TcpHealthCheck) this.healthChecker_;
        }
        return TcpHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public GrpcHealthCheck getGrpcHealthCheck() {
        if (this.healthCheckerCase_ == 11) {
            return (GrpcHealthCheck) this.healthChecker_;
        }
        return GrpcHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public GrpcHealthCheckOrBuilder getGrpcHealthCheckOrBuilder() {
        if (this.healthCheckerCase_ == 11) {
            return (GrpcHealthCheck) this.healthChecker_;
        }
        return GrpcHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public CustomHealthCheck getCustomHealthCheck() {
        if (this.healthCheckerCase_ == 13) {
            return (CustomHealthCheck) this.healthChecker_;
        }
        return CustomHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public CustomHealthCheckOrBuilder getCustomHealthCheckOrBuilder() {
        if (this.healthCheckerCase_ == 13) {
            return (CustomHealthCheck) this.healthChecker_;
        }
        return CustomHealthCheck.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getNoTrafficInterval() {
        Duration duration = this.noTrafficInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getNoTrafficIntervalOrBuilder() {
        return getNoTrafficInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getUnhealthyInterval() {
        Duration duration = this.unhealthyInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getUnhealthyIntervalOrBuilder() {
        return getUnhealthyInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getUnhealthyEdgeInterval() {
        Duration duration = this.unhealthyEdgeInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getUnhealthyEdgeIntervalOrBuilder() {
        return getUnhealthyEdgeInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Duration getHealthyEdgeInterval() {
        Duration duration = this.healthyEdgeInterval_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public DurationOrBuilder getHealthyEdgeIntervalOrBuilder() {
        return getHealthyEdgeInterval();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public String getEventLogPath() {
        Object obj = this.eventLogPath_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.eventLogPath_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public ByteString getEventLogPathBytes() {
        Object obj = this.eventLogPath_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.eventLogPath_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public EventServiceConfig getEventService() {
        EventServiceConfig eventServiceConfig = this.eventService_;
        return eventServiceConfig == null ? EventServiceConfig.getDefaultInstance() : eventServiceConfig;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public EventServiceConfigOrBuilder getEventServiceOrBuilder() {
        return getEventService();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public TlsOptions getTlsOptions() {
        TlsOptions tlsOptions = this.tlsOptions_;
        return tlsOptions == null ? TlsOptions.getDefaultInstance() : tlsOptions;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public TlsOptionsOrBuilder getTlsOptionsOrBuilder() {
        return getTlsOptions();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public Struct getTransportSocketMatchCriteria() {
        Struct struct = this.transportSocketMatchCriteria_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
    public StructOrBuilder getTransportSocketMatchCriteriaOrBuilder() {
        return getTransportSocketMatchCriteria();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.timeout_ != null) {
            codedOutputStream.writeMessage(1, getTimeout());
        }
        if (this.interval_ != null) {
            codedOutputStream.writeMessage(2, getInterval());
        }
        if (this.intervalJitter_ != null) {
            codedOutputStream.writeMessage(3, getIntervalJitter());
        }
        if (this.unhealthyThreshold_ != null) {
            codedOutputStream.writeMessage(4, getUnhealthyThreshold());
        }
        if (this.healthyThreshold_ != null) {
            codedOutputStream.writeMessage(5, getHealthyThreshold());
        }
        if (this.altPort_ != null) {
            codedOutputStream.writeMessage(6, getAltPort());
        }
        if (this.reuseConnection_ != null) {
            codedOutputStream.writeMessage(7, getReuseConnection());
        }
        if (this.healthCheckerCase_ == 8) {
            codedOutputStream.writeMessage(8, (HttpHealthCheck) this.healthChecker_);
        }
        if (this.healthCheckerCase_ == 9) {
            codedOutputStream.writeMessage(9, (TcpHealthCheck) this.healthChecker_);
        }
        if (this.healthCheckerCase_ == 11) {
            codedOutputStream.writeMessage(11, (GrpcHealthCheck) this.healthChecker_);
        }
        if (this.noTrafficInterval_ != null) {
            codedOutputStream.writeMessage(12, getNoTrafficInterval());
        }
        if (this.healthCheckerCase_ == 13) {
            codedOutputStream.writeMessage(13, (CustomHealthCheck) this.healthChecker_);
        }
        if (this.unhealthyInterval_ != null) {
            codedOutputStream.writeMessage(14, getUnhealthyInterval());
        }
        if (this.unhealthyEdgeInterval_ != null) {
            codedOutputStream.writeMessage(15, getUnhealthyEdgeInterval());
        }
        if (this.healthyEdgeInterval_ != null) {
            codedOutputStream.writeMessage(16, getHealthyEdgeInterval());
        }
        if (!getEventLogPathBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 17, this.eventLogPath_);
        }
        int i = this.intervalJitterPercent_;
        if (i != 0) {
            codedOutputStream.writeUInt32(18, i);
        }
        boolean z = this.alwaysLogHealthCheckFailures_;
        if (z) {
            codedOutputStream.writeBool(19, z);
        }
        if (this.initialJitter_ != null) {
            codedOutputStream.writeMessage(20, getInitialJitter());
        }
        if (this.tlsOptions_ != null) {
            codedOutputStream.writeMessage(21, getTlsOptions());
        }
        if (this.eventService_ != null) {
            codedOutputStream.writeMessage(22, getEventService());
        }
        if (this.transportSocketMatchCriteria_ != null) {
            codedOutputStream.writeMessage(23, getTransportSocketMatchCriteria());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.timeout_ != null ? CodedOutputStream.computeMessageSize(1, getTimeout()) : 0;
        if (this.interval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getInterval());
        }
        if (this.intervalJitter_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getIntervalJitter());
        }
        if (this.unhealthyThreshold_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getUnhealthyThreshold());
        }
        if (this.healthyThreshold_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, getHealthyThreshold());
        }
        if (this.altPort_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, getAltPort());
        }
        if (this.reuseConnection_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(7, getReuseConnection());
        }
        if (this.healthCheckerCase_ == 8) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(8, (HttpHealthCheck) this.healthChecker_);
        }
        if (this.healthCheckerCase_ == 9) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(9, (TcpHealthCheck) this.healthChecker_);
        }
        if (this.healthCheckerCase_ == 11) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(11, (GrpcHealthCheck) this.healthChecker_);
        }
        if (this.noTrafficInterval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(12, getNoTrafficInterval());
        }
        if (this.healthCheckerCase_ == 13) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(13, (CustomHealthCheck) this.healthChecker_);
        }
        if (this.unhealthyInterval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(14, getUnhealthyInterval());
        }
        if (this.unhealthyEdgeInterval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(15, getUnhealthyEdgeInterval());
        }
        if (this.healthyEdgeInterval_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(16, getHealthyEdgeInterval());
        }
        if (!getEventLogPathBytes().isEmpty()) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(17, this.eventLogPath_);
        }
        int i2 = this.intervalJitterPercent_;
        if (i2 != 0) {
            iComputeMessageSize += CodedOutputStream.computeUInt32Size(18, i2);
        }
        boolean z = this.alwaysLogHealthCheckFailures_;
        if (z) {
            iComputeMessageSize += CodedOutputStream.computeBoolSize(19, z);
        }
        if (this.initialJitter_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(20, getInitialJitter());
        }
        if (this.tlsOptions_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(21, getTlsOptions());
        }
        if (this.eventService_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(22, getEventService());
        }
        if (this.transportSocketMatchCriteria_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(23, getTransportSocketMatchCriteria());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HealthCheck)) {
            return super.equals(obj);
        }
        HealthCheck healthCheck = (HealthCheck) obj;
        if (hasTimeout() != healthCheck.hasTimeout()) {
            return false;
        }
        if ((hasTimeout() && !getTimeout().equals(healthCheck.getTimeout())) || hasInterval() != healthCheck.hasInterval()) {
            return false;
        }
        if ((hasInterval() && !getInterval().equals(healthCheck.getInterval())) || hasInitialJitter() != healthCheck.hasInitialJitter()) {
            return false;
        }
        if ((hasInitialJitter() && !getInitialJitter().equals(healthCheck.getInitialJitter())) || hasIntervalJitter() != healthCheck.hasIntervalJitter()) {
            return false;
        }
        if ((hasIntervalJitter() && !getIntervalJitter().equals(healthCheck.getIntervalJitter())) || getIntervalJitterPercent() != healthCheck.getIntervalJitterPercent() || hasUnhealthyThreshold() != healthCheck.hasUnhealthyThreshold()) {
            return false;
        }
        if ((hasUnhealthyThreshold() && !getUnhealthyThreshold().equals(healthCheck.getUnhealthyThreshold())) || hasHealthyThreshold() != healthCheck.hasHealthyThreshold()) {
            return false;
        }
        if ((hasHealthyThreshold() && !getHealthyThreshold().equals(healthCheck.getHealthyThreshold())) || hasAltPort() != healthCheck.hasAltPort()) {
            return false;
        }
        if ((hasAltPort() && !getAltPort().equals(healthCheck.getAltPort())) || hasReuseConnection() != healthCheck.hasReuseConnection()) {
            return false;
        }
        if ((hasReuseConnection() && !getReuseConnection().equals(healthCheck.getReuseConnection())) || hasNoTrafficInterval() != healthCheck.hasNoTrafficInterval()) {
            return false;
        }
        if ((hasNoTrafficInterval() && !getNoTrafficInterval().equals(healthCheck.getNoTrafficInterval())) || hasUnhealthyInterval() != healthCheck.hasUnhealthyInterval()) {
            return false;
        }
        if ((hasUnhealthyInterval() && !getUnhealthyInterval().equals(healthCheck.getUnhealthyInterval())) || hasUnhealthyEdgeInterval() != healthCheck.hasUnhealthyEdgeInterval()) {
            return false;
        }
        if ((hasUnhealthyEdgeInterval() && !getUnhealthyEdgeInterval().equals(healthCheck.getUnhealthyEdgeInterval())) || hasHealthyEdgeInterval() != healthCheck.hasHealthyEdgeInterval()) {
            return false;
        }
        if ((hasHealthyEdgeInterval() && !getHealthyEdgeInterval().equals(healthCheck.getHealthyEdgeInterval())) || !getEventLogPath().equals(healthCheck.getEventLogPath()) || hasEventService() != healthCheck.hasEventService()) {
            return false;
        }
        if ((hasEventService() && !getEventService().equals(healthCheck.getEventService())) || getAlwaysLogHealthCheckFailures() != healthCheck.getAlwaysLogHealthCheckFailures() || hasTlsOptions() != healthCheck.hasTlsOptions()) {
            return false;
        }
        if ((hasTlsOptions() && !getTlsOptions().equals(healthCheck.getTlsOptions())) || hasTransportSocketMatchCriteria() != healthCheck.hasTransportSocketMatchCriteria()) {
            return false;
        }
        if ((hasTransportSocketMatchCriteria() && !getTransportSocketMatchCriteria().equals(healthCheck.getTransportSocketMatchCriteria())) || !getHealthCheckerCase().equals(healthCheck.getHealthCheckerCase())) {
            return false;
        }
        int i = this.healthCheckerCase_;
        if (i != 8) {
            if (i != 9) {
                if (i == 11) {
                    if (!getGrpcHealthCheck().equals(healthCheck.getGrpcHealthCheck())) {
                        return false;
                    }
                } else if (i == 13 && !getCustomHealthCheck().equals(healthCheck.getCustomHealthCheck())) {
                    return false;
                }
            } else if (!getTcpHealthCheck().equals(healthCheck.getTcpHealthCheck())) {
                return false;
            }
        } else if (!getHttpHealthCheck().equals(healthCheck.getHttpHealthCheck())) {
            return false;
        }
        return this.unknownFields.equals(healthCheck.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasTimeout()) {
            iHashCode2 = (((iHashCode2 * 37) + 1) * 53) + getTimeout().hashCode();
        }
        if (hasInterval()) {
            iHashCode2 = (((iHashCode2 * 37) + 2) * 53) + getInterval().hashCode();
        }
        if (hasInitialJitter()) {
            iHashCode2 = (((iHashCode2 * 37) + 20) * 53) + getInitialJitter().hashCode();
        }
        if (hasIntervalJitter()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getIntervalJitter().hashCode();
        }
        int intervalJitterPercent = (((iHashCode2 * 37) + 18) * 53) + getIntervalJitterPercent();
        if (hasUnhealthyThreshold()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 4) * 53) + getUnhealthyThreshold().hashCode();
        }
        if (hasHealthyThreshold()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 5) * 53) + getHealthyThreshold().hashCode();
        }
        if (hasAltPort()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 6) * 53) + getAltPort().hashCode();
        }
        if (hasReuseConnection()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 7) * 53) + getReuseConnection().hashCode();
        }
        if (hasNoTrafficInterval()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 12) * 53) + getNoTrafficInterval().hashCode();
        }
        if (hasUnhealthyInterval()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 14) * 53) + getUnhealthyInterval().hashCode();
        }
        if (hasUnhealthyEdgeInterval()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 15) * 53) + getUnhealthyEdgeInterval().hashCode();
        }
        if (hasHealthyEdgeInterval()) {
            intervalJitterPercent = (((intervalJitterPercent * 37) + 16) * 53) + getHealthyEdgeInterval().hashCode();
        }
        int iHashCode3 = (((intervalJitterPercent * 37) + 17) * 53) + getEventLogPath().hashCode();
        if (hasEventService()) {
            iHashCode3 = (((iHashCode3 * 37) + 22) * 53) + getEventService().hashCode();
        }
        int iHashBoolean = (((iHashCode3 * 37) + 19) * 53) + Internal.hashBoolean(getAlwaysLogHealthCheckFailures());
        if (hasTlsOptions()) {
            iHashBoolean = (((iHashBoolean * 37) + 21) * 53) + getTlsOptions().hashCode();
        }
        if (hasTransportSocketMatchCriteria()) {
            iHashBoolean = (((iHashBoolean * 37) + 23) * 53) + getTransportSocketMatchCriteria().hashCode();
        }
        int i2 = this.healthCheckerCase_;
        if (i2 == 8) {
            i = ((iHashBoolean * 37) + 8) * 53;
            iHashCode = getHttpHealthCheck().hashCode();
        } else if (i2 == 9) {
            i = ((iHashBoolean * 37) + 9) * 53;
            iHashCode = getTcpHealthCheck().hashCode();
        } else if (i2 == 11) {
            i = ((iHashBoolean * 37) + 11) * 53;
            iHashCode = getGrpcHealthCheck().hashCode();
        } else {
            if (i2 == 13) {
                i = ((iHashBoolean * 37) + 13) * 53;
                iHashCode = getCustomHealthCheck().hashCode();
            }
            int iHashCode4 = (iHashBoolean * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode4;
            return iHashCode4;
        }
        iHashBoolean = i + iHashCode;
        int iHashCode42 = (iHashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode42;
        return iHashCode42;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m22855newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m22858toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum HealthCheckerCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        HTTP_HEALTH_CHECK(8),
        TCP_HEALTH_CHECK(9),
        GRPC_HEALTH_CHECK(11),
        CUSTOM_HEALTH_CHECK(13),
        HEALTHCHECKER_NOT_SET(0);

        private final int value;

        HealthCheckerCase(int i) {
            this.value = i;
        }

        public static HealthCheckerCase forNumber(int i) {
            if (i == 0) {
                return HEALTHCHECKER_NOT_SET;
            }
            if (i == 11) {
                return GRPC_HEALTH_CHECK;
            }
            if (i == 13) {
                return CUSTOM_HEALTH_CHECK;
            }
            if (i == 8) {
                return HTTP_HEALTH_CHECK;
            }
            if (i != 9) {
                return null;
            }
            return TCP_HEALTH_CHECK;
        }

        @Deprecated
        public static HealthCheckerCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface CustomHealthCheckOrBuilder extends MessageOrBuilder {
        CustomHealthCheck.ConfigTypeCase getConfigTypeCase();

        String getName();

        ByteString getNameBytes();

        Any getTypedConfig();

        AnyOrBuilder getTypedConfigOrBuilder();

        boolean hasTypedConfig();
    }

    public interface GrpcHealthCheckOrBuilder extends MessageOrBuilder {
        String getAuthority();

        ByteString getAuthorityBytes();

        String getServiceName();

        ByteString getServiceNameBytes();
    }

    public interface HttpHealthCheckOrBuilder extends MessageOrBuilder {
        CodecClientType getCodecClientType();

        int getCodecClientTypeValue();

        Int64Range getExpectedStatuses(int i);

        int getExpectedStatusesCount();

        List<Int64Range> getExpectedStatusesList();

        Int64RangeOrBuilder getExpectedStatusesOrBuilder(int i);

        List<? extends Int64RangeOrBuilder> getExpectedStatusesOrBuilderList();

        String getHost();

        ByteString getHostBytes();

        String getPath();

        ByteString getPathBytes();

        Payload getReceive();

        PayloadOrBuilder getReceiveOrBuilder();

        HeaderValueOption getRequestHeadersToAdd(int i);

        int getRequestHeadersToAddCount();

        List<HeaderValueOption> getRequestHeadersToAddList();

        HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i);

        List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList();

        String getRequestHeadersToRemove(int i);

        ByteString getRequestHeadersToRemoveBytes(int i);

        int getRequestHeadersToRemoveCount();

        /* renamed from: getRequestHeadersToRemoveList */
        List<String> mo22993getRequestHeadersToRemoveList();

        Payload getSend();

        PayloadOrBuilder getSendOrBuilder();

        StringMatcher getServiceNameMatcher();

        StringMatcherOrBuilder getServiceNameMatcherOrBuilder();

        boolean hasReceive();

        boolean hasSend();

        boolean hasServiceNameMatcher();
    }

    public interface PayloadOrBuilder extends MessageOrBuilder {
        ByteString getBinary();

        Payload.PayloadCase getPayloadCase();

        String getText();

        ByteString getTextBytes();
    }

    public interface RedisHealthCheckOrBuilder extends MessageOrBuilder {
        String getKey();

        ByteString getKeyBytes();
    }

    public interface TcpHealthCheckOrBuilder extends MessageOrBuilder {
        Payload getReceive(int i);

        int getReceiveCount();

        List<Payload> getReceiveList();

        PayloadOrBuilder getReceiveOrBuilder(int i);

        List<? extends PayloadOrBuilder> getReceiveOrBuilderList();

        Payload getSend();

        PayloadOrBuilder getSendOrBuilder();

        boolean hasSend();
    }

    public interface TlsOptionsOrBuilder extends MessageOrBuilder {
        String getAlpnProtocols(int i);

        ByteString getAlpnProtocolsBytes(int i);

        int getAlpnProtocolsCount();

        /* renamed from: getAlpnProtocolsList */
        List<String> mo23176getAlpnProtocolsList();
    }

    public static final class Payload extends GeneratedMessageV3 implements PayloadOrBuilder {
        public static final int BINARY_FIELD_NUMBER = 2;
        public static final int TEXT_FIELD_NUMBER = 1;
        private static final Payload DEFAULT_INSTANCE = new Payload();
        private static final Parser<Payload> PARSER = new AbstractParser<Payload>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Payload m23045parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Payload(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private int payloadCase_;
        private Object payload_;

        private Payload(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.payloadCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Payload() {
            this.payloadCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private Payload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.payloadCase_ = 1;
                                this.payload_ = stringRequireUtf8;
                            } else if (tag == 18) {
                                this.payloadCase_ = 2;
                                this.payload_ = codedInputStream.readBytes();
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

        public static Payload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Payload> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_Payload_descriptor;
        }

        public static Payload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(byteBuffer);
        }

        public static Payload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Payload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(byteString);
        }

        public static Payload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Payload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(bArr);
        }

        public static Payload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Payload) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Payload parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Payload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Payload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Payload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Payload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Payload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m23043toBuilder();
        }

        public static Builder newBuilder(Payload payload) {
            return DEFAULT_INSTANCE.m23043toBuilder().mergeFrom(payload);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Payload m23038getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<Payload> getParserForType() {
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
            return new Payload();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(Payload.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
        public String getText() {
            String str = this.payloadCase_ == 1 ? this.payload_ : "";
            if (str instanceof String) {
                return (String) str;
            }
            String stringUtf8 = ((ByteString) str).toStringUtf8();
            if (this.payloadCase_ == 1) {
                this.payload_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
        public ByteString getTextBytes() {
            String str = this.payloadCase_ == 1 ? this.payload_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.payloadCase_ == 1) {
                    this.payload_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
        public ByteString getBinary() {
            if (this.payloadCase_ == 2) {
                return (ByteString) this.payload_;
            }
            return ByteString.EMPTY;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.payloadCase_ == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.payload_);
            }
            if (this.payloadCase_ == 2) {
                codedOutputStream.writeBytes(2, (ByteString) this.payload_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = this.payloadCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.payload_) : 0;
            if (this.payloadCase_ == 2) {
                iComputeStringSize += CodedOutputStream.computeBytesSize(2, (ByteString) this.payload_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Payload)) {
                return super.equals(obj);
            }
            Payload payload = (Payload) obj;
            if (!getPayloadCase().equals(payload.getPayloadCase())) {
                return false;
            }
            int i = this.payloadCase_;
            if (i == 1) {
                if (!getText().equals(payload.getText())) {
                    return false;
                }
            } else if (i == 2 && !getBinary().equals(payload.getBinary())) {
                return false;
            }
            return this.unknownFields.equals(payload.unknownFields);
        }

        public int hashCode() {
            int i;
            int iHashCode;
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode2 = 779 + getDescriptor().hashCode();
            int i2 = this.payloadCase_;
            if (i2 == 1) {
                i = ((iHashCode2 * 37) + 1) * 53;
                iHashCode = getText().hashCode();
            } else {
                if (i2 == 2) {
                    i = ((iHashCode2 * 37) + 2) * 53;
                    iHashCode = getBinary().hashCode();
                }
                int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode3;
                return iHashCode3;
            }
            iHashCode2 = i + iHashCode;
            int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode32;
            return iHashCode32;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23040newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23043toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum PayloadCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            TEXT(1),
            BINARY(2),
            PAYLOAD_NOT_SET(0);

            private final int value;

            PayloadCase(int i) {
                this.value = i;
            }

            public static PayloadCase forNumber(int i) {
                if (i == 0) {
                    return PAYLOAD_NOT_SET;
                }
                if (i == 1) {
                    return TEXT;
                }
                if (i != 2) {
                    return null;
                }
                return BINARY;
            }

            @Deprecated
            public static PayloadCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PayloadOrBuilder {
            private int payloadCase_;
            private Object payload_;

            private Builder() {
                this.payloadCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.payloadCase_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_Payload_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(Payload.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Payload.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23054clear() {
                super.clear();
                this.payloadCase_ = 0;
                this.payload_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_Payload_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Payload m23067getDefaultInstanceForType() {
                return Payload.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Payload m23048build() throws UninitializedMessageException {
                Payload payloadM23050buildPartial = m23050buildPartial();
                if (payloadM23050buildPartial.isInitialized()) {
                    return payloadM23050buildPartial;
                }
                throw newUninitializedMessageException(payloadM23050buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Payload m23050buildPartial() {
                Payload payload = new Payload(this);
                if (this.payloadCase_ == 1) {
                    payload.payload_ = this.payload_;
                }
                if (this.payloadCase_ == 2) {
                    payload.payload_ = this.payload_;
                }
                payload.payloadCase_ = this.payloadCase_;
                onBuilt();
                return payload;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23066clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23078setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23056clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23059clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23080setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23046addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23071mergeFrom(Message message) {
                if (message instanceof Payload) {
                    return mergeFrom((Payload) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Payload payload) {
                if (payload == Payload.getDefaultInstance()) {
                    return this;
                }
                int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$Payload$PayloadCase[payload.getPayloadCase().ordinal()];
                if (i == 1) {
                    this.payloadCase_ = 1;
                    this.payload_ = payload.payload_;
                    onChanged();
                } else if (i == 2) {
                    setBinary(payload.getBinary());
                }
                m23076mergeUnknownFields(payload.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload.Builder m23072mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$Payload r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$Payload r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Payload.Builder.m23072mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$Payload$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
            public PayloadCase getPayloadCase() {
                return PayloadCase.forNumber(this.payloadCase_);
            }

            public Builder clearPayload() {
                this.payloadCase_ = 0;
                this.payload_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
            public String getText() {
                String str = this.payloadCase_ == 1 ? this.payload_ : "";
                if (!(str instanceof String)) {
                    String stringUtf8 = ((ByteString) str).toStringUtf8();
                    if (this.payloadCase_ == 1) {
                        this.payload_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) str;
            }

            public Builder setText(String str) {
                str.getClass();
                this.payloadCase_ = 1;
                this.payload_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
            public ByteString getTextBytes() {
                String str = this.payloadCase_ == 1 ? this.payload_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.payloadCase_ == 1) {
                        this.payload_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            public Builder setTextBytes(ByteString byteString) {
                byteString.getClass();
                Payload.checkByteStringIsUtf8(byteString);
                this.payloadCase_ = 1;
                this.payload_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearText() {
                if (this.payloadCase_ == 1) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.PayloadOrBuilder
            public ByteString getBinary() {
                if (this.payloadCase_ == 2) {
                    return (ByteString) this.payload_;
                }
                return ByteString.EMPTY;
            }

            public Builder setBinary(ByteString byteString) {
                byteString.getClass();
                this.payloadCase_ = 2;
                this.payload_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearBinary() {
                if (this.payloadCase_ == 2) {
                    this.payloadCase_ = 0;
                    this.payload_ = null;
                    onChanged();
                }
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23082setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23076mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class HttpHealthCheck extends GeneratedMessageV3 implements HttpHealthCheckOrBuilder {
        public static final int CODEC_CLIENT_TYPE_FIELD_NUMBER = 10;
        public static final int EXPECTED_STATUSES_FIELD_NUMBER = 9;
        public static final int HOST_FIELD_NUMBER = 1;
        public static final int PATH_FIELD_NUMBER = 2;
        public static final int RECEIVE_FIELD_NUMBER = 4;
        public static final int REQUEST_HEADERS_TO_ADD_FIELD_NUMBER = 6;
        public static final int REQUEST_HEADERS_TO_REMOVE_FIELD_NUMBER = 8;
        public static final int SEND_FIELD_NUMBER = 3;
        public static final int SERVICE_NAME_MATCHER_FIELD_NUMBER = 11;
        private static final long serialVersionUID = 0;
        private static final HttpHealthCheck DEFAULT_INSTANCE = new HttpHealthCheck();
        private static final Parser<HttpHealthCheck> PARSER = new AbstractParser<HttpHealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public HttpHealthCheck m22999parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new HttpHealthCheck(codedInputStream, extensionRegistryLite);
            }
        };
        private int codecClientType_;
        private List<Int64Range> expectedStatuses_;
        private volatile Object host_;
        private byte memoizedIsInitialized;
        private volatile Object path_;
        private Payload receive_;
        private List<HeaderValueOption> requestHeadersToAdd_;
        private LazyStringList requestHeadersToRemove_;
        private Payload send_;
        private StringMatcher serviceNameMatcher_;

        private HttpHealthCheck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private HttpHealthCheck() {
            this.memoizedIsInitialized = (byte) -1;
            this.host_ = "";
            this.path_ = "";
            this.requestHeadersToAdd_ = Collections.emptyList();
            this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
            this.expectedStatuses_ = Collections.emptyList();
            this.codecClientType_ = 0;
        }

        private HttpHealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.host_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag != 18) {
                                if (tag == 26) {
                                    Payload payload = this.send_;
                                    Payload.Builder builderM23043toBuilder = payload != null ? payload.m23043toBuilder() : null;
                                    Payload payload2 = (Payload) codedInputStream.readMessage(Payload.parser(), extensionRegistryLite);
                                    this.send_ = payload2;
                                    if (builderM23043toBuilder != null) {
                                        builderM23043toBuilder.mergeFrom(payload2);
                                        this.send_ = builderM23043toBuilder.m23050buildPartial();
                                    }
                                } else if (tag == 34) {
                                    Payload payload3 = this.receive_;
                                    Payload.Builder builderM23043toBuilder2 = payload3 != null ? payload3.m23043toBuilder() : null;
                                    Payload payload4 = (Payload) codedInputStream.readMessage(Payload.parser(), extensionRegistryLite);
                                    this.receive_ = payload4;
                                    if (builderM23043toBuilder2 != null) {
                                        builderM23043toBuilder2.mergeFrom(payload4);
                                        this.receive_ = builderM23043toBuilder2.m23050buildPartial();
                                    }
                                } else if (tag == 50) {
                                    if ((i & 1) == 0) {
                                        this.requestHeadersToAdd_ = new ArrayList();
                                        i |= 1;
                                    }
                                    this.requestHeadersToAdd_.add(codedInputStream.readMessage(HeaderValueOption.parser(), extensionRegistryLite));
                                } else if (tag == 66) {
                                    String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if ((i & 2) == 0) {
                                        this.requestHeadersToRemove_ = new LazyStringArrayList();
                                        i |= 2;
                                    }
                                    this.requestHeadersToRemove_.add(stringRequireUtf8);
                                } else if (tag == 74) {
                                    if ((i & 4) == 0) {
                                        this.expectedStatuses_ = new ArrayList();
                                        i |= 4;
                                    }
                                    this.expectedStatuses_.add(codedInputStream.readMessage(Int64Range.parser(), extensionRegistryLite));
                                } else if (tag == 80) {
                                    this.codecClientType_ = codedInputStream.readEnum();
                                } else if (tag == 90) {
                                    StringMatcher stringMatcher = this.serviceNameMatcher_;
                                    StringMatcher.Builder builderM33826toBuilder = stringMatcher != null ? stringMatcher.m33826toBuilder() : null;
                                    StringMatcher stringMatcher2 = (StringMatcher) codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite);
                                    this.serviceNameMatcher_ = stringMatcher2;
                                    if (builderM33826toBuilder != null) {
                                        builderM33826toBuilder.mergeFrom(stringMatcher2);
                                        this.serviceNameMatcher_ = builderM33826toBuilder.m33833buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.path_ = codedInputStream.readStringRequireUtf8();
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 1) != 0) {
                        this.requestHeadersToAdd_ = Collections.unmodifiableList(this.requestHeadersToAdd_);
                    }
                    if ((i & 2) != 0) {
                        this.requestHeadersToRemove_ = this.requestHeadersToRemove_.getUnmodifiableView();
                    }
                    if ((i & 4) != 0) {
                        this.expectedStatuses_ = Collections.unmodifiableList(this.expectedStatuses_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static HttpHealthCheck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HttpHealthCheck> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_HttpHealthCheck_descriptor;
        }

        public static HttpHealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(byteBuffer);
        }

        public static HttpHealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static HttpHealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(byteString);
        }

        public static HttpHealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static HttpHealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(bArr);
        }

        public static HttpHealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (HttpHealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static HttpHealthCheck parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static HttpHealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HttpHealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static HttpHealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static HttpHealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static HttpHealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m22997toBuilder();
        }

        public static Builder newBuilder(HttpHealthCheck httpHealthCheck) {
            return DEFAULT_INSTANCE.m22997toBuilder().mergeFrom(httpHealthCheck);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public int getCodecClientTypeValue() {
            return this.codecClientType_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HttpHealthCheck m22991getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public List<Int64Range> getExpectedStatusesList() {
            return this.expectedStatuses_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public List<? extends Int64RangeOrBuilder> getExpectedStatusesOrBuilderList() {
            return this.expectedStatuses_;
        }

        public Parser<HttpHealthCheck> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public List<HeaderValueOption> getRequestHeadersToAddList() {
            return this.requestHeadersToAdd_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList() {
            return this.requestHeadersToAdd_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        /* renamed from: getRequestHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo22993getRequestHeadersToRemoveList() {
            return this.requestHeadersToRemove_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public boolean hasReceive() {
            return this.receive_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public boolean hasSend() {
            return this.send_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public boolean hasServiceNameMatcher() {
            return this.serviceNameMatcher_ != null;
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
            return new HttpHealthCheck();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_HttpHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpHealthCheck.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public String getHost() {
            Object obj = this.host_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.host_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public ByteString getHostBytes() {
            Object obj = this.host_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.host_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public String getPath() {
            Object obj = this.path_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.path_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public ByteString getPathBytes() {
            Object obj = this.path_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.path_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public Payload getSend() {
            Payload payload = this.send_;
            return payload == null ? Payload.getDefaultInstance() : payload;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public PayloadOrBuilder getSendOrBuilder() {
            return getSend();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public Payload getReceive() {
            Payload payload = this.receive_;
            return payload == null ? Payload.getDefaultInstance() : payload;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public PayloadOrBuilder getReceiveOrBuilder() {
            return getReceive();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public int getRequestHeadersToAddCount() {
            return this.requestHeadersToAdd_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public HeaderValueOption getRequestHeadersToAdd(int i) {
            return this.requestHeadersToAdd_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i) {
            return this.requestHeadersToAdd_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public int getRequestHeadersToRemoveCount() {
            return this.requestHeadersToRemove_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public String getRequestHeadersToRemove(int i) {
            return (String) this.requestHeadersToRemove_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public ByteString getRequestHeadersToRemoveBytes(int i) {
            return this.requestHeadersToRemove_.getByteString(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public int getExpectedStatusesCount() {
            return this.expectedStatuses_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public Int64Range getExpectedStatuses(int i) {
            return this.expectedStatuses_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public Int64RangeOrBuilder getExpectedStatusesOrBuilder(int i) {
            return this.expectedStatuses_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public CodecClientType getCodecClientType() {
            CodecClientType codecClientTypeValueOf = CodecClientType.valueOf(this.codecClientType_);
            return codecClientTypeValueOf == null ? CodecClientType.UNRECOGNIZED : codecClientTypeValueOf;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public StringMatcher getServiceNameMatcher() {
            StringMatcher stringMatcher = this.serviceNameMatcher_;
            return stringMatcher == null ? StringMatcher.getDefaultInstance() : stringMatcher;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
        public StringMatcherOrBuilder getServiceNameMatcherOrBuilder() {
            return getServiceNameMatcher();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getHostBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.host_);
            }
            if (!getPathBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.path_);
            }
            if (this.send_ != null) {
                codedOutputStream.writeMessage(3, getSend());
            }
            if (this.receive_ != null) {
                codedOutputStream.writeMessage(4, getReceive());
            }
            for (int i = 0; i < this.requestHeadersToAdd_.size(); i++) {
                codedOutputStream.writeMessage(6, this.requestHeadersToAdd_.get(i));
            }
            for (int i2 = 0; i2 < this.requestHeadersToRemove_.size(); i2++) {
                GeneratedMessageV3.writeString(codedOutputStream, 8, this.requestHeadersToRemove_.getRaw(i2));
            }
            for (int i3 = 0; i3 < this.expectedStatuses_.size(); i3++) {
                codedOutputStream.writeMessage(9, this.expectedStatuses_.get(i3));
            }
            if (this.codecClientType_ != CodecClientType.HTTP1.getNumber()) {
                codedOutputStream.writeEnum(10, this.codecClientType_);
            }
            if (this.serviceNameMatcher_ != null) {
                codedOutputStream.writeMessage(11, getServiceNameMatcher());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getHostBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.host_) : 0;
            if (!getPathBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.path_);
            }
            if (this.send_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, getSend());
            }
            if (this.receive_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, getReceive());
            }
            for (int i2 = 0; i2 < this.requestHeadersToAdd_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(6, this.requestHeadersToAdd_.get(i2));
            }
            int iComputeStringSizeNoTag = 0;
            for (int i3 = 0; i3 < this.requestHeadersToRemove_.size(); i3++) {
                iComputeStringSizeNoTag += computeStringSizeNoTag(this.requestHeadersToRemove_.getRaw(i3));
            }
            int size = iComputeStringSize + iComputeStringSizeNoTag + mo22993getRequestHeadersToRemoveList().size();
            for (int i4 = 0; i4 < this.expectedStatuses_.size(); i4++) {
                size += CodedOutputStream.computeMessageSize(9, this.expectedStatuses_.get(i4));
            }
            if (this.codecClientType_ != CodecClientType.HTTP1.getNumber()) {
                size += CodedOutputStream.computeEnumSize(10, this.codecClientType_);
            }
            if (this.serviceNameMatcher_ != null) {
                size += CodedOutputStream.computeMessageSize(11, getServiceNameMatcher());
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof HttpHealthCheck)) {
                return super.equals(obj);
            }
            HttpHealthCheck httpHealthCheck = (HttpHealthCheck) obj;
            if (!getHost().equals(httpHealthCheck.getHost()) || !getPath().equals(httpHealthCheck.getPath()) || hasSend() != httpHealthCheck.hasSend()) {
                return false;
            }
            if ((hasSend() && !getSend().equals(httpHealthCheck.getSend())) || hasReceive() != httpHealthCheck.hasReceive()) {
                return false;
            }
            if ((!hasReceive() || getReceive().equals(httpHealthCheck.getReceive())) && getRequestHeadersToAddList().equals(httpHealthCheck.getRequestHeadersToAddList()) && mo22993getRequestHeadersToRemoveList().equals(httpHealthCheck.mo22993getRequestHeadersToRemoveList()) && getExpectedStatusesList().equals(httpHealthCheck.getExpectedStatusesList()) && this.codecClientType_ == httpHealthCheck.codecClientType_ && hasServiceNameMatcher() == httpHealthCheck.hasServiceNameMatcher()) {
                return (!hasServiceNameMatcher() || getServiceNameMatcher().equals(httpHealthCheck.getServiceNameMatcher())) && this.unknownFields.equals(httpHealthCheck.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHost().hashCode()) * 37) + 2) * 53) + getPath().hashCode();
            if (hasSend()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getSend().hashCode();
            }
            if (hasReceive()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getReceive().hashCode();
            }
            if (getRequestHeadersToAddCount() > 0) {
                iHashCode = (((iHashCode * 37) + 6) * 53) + getRequestHeadersToAddList().hashCode();
            }
            if (getRequestHeadersToRemoveCount() > 0) {
                iHashCode = (((iHashCode * 37) + 8) * 53) + mo22993getRequestHeadersToRemoveList().hashCode();
            }
            if (getExpectedStatusesCount() > 0) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + getExpectedStatusesList().hashCode();
            }
            int iHashCode2 = (((iHashCode * 37) + 10) * 53) + this.codecClientType_;
            if (hasServiceNameMatcher()) {
                iHashCode2 = (((iHashCode2 * 37) + 11) * 53) + getServiceNameMatcher().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22994newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22997toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HttpHealthCheckOrBuilder {
            private int bitField0_;
            private int codecClientType_;
            private RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> expectedStatusesBuilder_;
            private List<Int64Range> expectedStatuses_;
            private Object host_;
            private Object path_;
            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> receiveBuilder_;
            private Payload receive_;
            private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> requestHeadersToAddBuilder_;
            private List<HeaderValueOption> requestHeadersToAdd_;
            private LazyStringList requestHeadersToRemove_;
            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> sendBuilder_;
            private Payload send_;
            private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> serviceNameMatcherBuilder_;
            private StringMatcher serviceNameMatcher_;

            private Builder() {
                this.host_ = "";
                this.path_ = "";
                this.requestHeadersToAdd_ = Collections.emptyList();
                this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
                this.expectedStatuses_ = Collections.emptyList();
                this.codecClientType_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.host_ = "";
                this.path_ = "";
                this.requestHeadersToAdd_ = Collections.emptyList();
                this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
                this.expectedStatuses_ = Collections.emptyList();
                this.codecClientType_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_HttpHealthCheck_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public int getCodecClientTypeValue() {
                return this.codecClientType_;
            }

            public Builder setCodecClientTypeValue(int i) {
                this.codecClientType_ = i;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public boolean hasReceive() {
                return (this.receiveBuilder_ == null && this.receive_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public boolean hasSend() {
                return (this.sendBuilder_ == null && this.send_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public boolean hasServiceNameMatcher() {
                return (this.serviceNameMatcherBuilder_ == null && this.serviceNameMatcher_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_HttpHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(HttpHealthCheck.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (HttpHealthCheck.alwaysUseFieldBuilders) {
                    getRequestHeadersToAddFieldBuilder();
                    getExpectedStatusesFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23008clear() {
                super.clear();
                this.host_ = "";
                this.path_ = "";
                if (this.sendBuilder_ == null) {
                    this.send_ = null;
                } else {
                    this.send_ = null;
                    this.sendBuilder_ = null;
                }
                if (this.receiveBuilder_ == null) {
                    this.receive_ = null;
                } else {
                    this.receive_ = null;
                    this.receiveBuilder_ = null;
                }
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.requestHeadersToAdd_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV32 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    this.expectedStatuses_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV32.clear();
                }
                this.codecClientType_ = 0;
                if (this.serviceNameMatcherBuilder_ == null) {
                    this.serviceNameMatcher_ = null;
                } else {
                    this.serviceNameMatcher_ = null;
                    this.serviceNameMatcherBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_HttpHealthCheck_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HttpHealthCheck m23021getDefaultInstanceForType() {
                return HttpHealthCheck.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HttpHealthCheck m23002build() throws UninitializedMessageException {
                HttpHealthCheck httpHealthCheckM23004buildPartial = m23004buildPartial();
                if (httpHealthCheckM23004buildPartial.isInitialized()) {
                    return httpHealthCheckM23004buildPartial;
                }
                throw newUninitializedMessageException(httpHealthCheckM23004buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public HttpHealthCheck m23004buildPartial() {
                HttpHealthCheck httpHealthCheck = new HttpHealthCheck(this);
                httpHealthCheck.host_ = this.host_;
                httpHealthCheck.path_ = this.path_;
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    httpHealthCheck.send_ = this.send_;
                } else {
                    httpHealthCheck.send_ = singleFieldBuilderV3.build();
                }
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV32 = this.receiveBuilder_;
                if (singleFieldBuilderV32 == null) {
                    httpHealthCheck.receive_ = this.receive_;
                } else {
                    httpHealthCheck.receive_ = singleFieldBuilderV32.build();
                }
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.requestHeadersToAdd_ = Collections.unmodifiableList(this.requestHeadersToAdd_);
                        this.bitField0_ &= -2;
                    }
                    httpHealthCheck.requestHeadersToAdd_ = this.requestHeadersToAdd_;
                } else {
                    httpHealthCheck.requestHeadersToAdd_ = repeatedFieldBuilderV3.build();
                }
                if ((this.bitField0_ & 2) != 0) {
                    this.requestHeadersToRemove_ = this.requestHeadersToRemove_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                httpHealthCheck.requestHeadersToRemove_ = this.requestHeadersToRemove_;
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV32 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    if ((this.bitField0_ & 4) != 0) {
                        this.expectedStatuses_ = Collections.unmodifiableList(this.expectedStatuses_);
                        this.bitField0_ &= -5;
                    }
                    httpHealthCheck.expectedStatuses_ = this.expectedStatuses_;
                } else {
                    httpHealthCheck.expectedStatuses_ = repeatedFieldBuilderV32.build();
                }
                httpHealthCheck.codecClientType_ = this.codecClientType_;
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV33 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV33 == null) {
                    httpHealthCheck.serviceNameMatcher_ = this.serviceNameMatcher_;
                } else {
                    httpHealthCheck.serviceNameMatcher_ = singleFieldBuilderV33.build();
                }
                onBuilt();
                return httpHealthCheck;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23020clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23032setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23010clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23013clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23034setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23000addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23025mergeFrom(Message message) {
                if (message instanceof HttpHealthCheck) {
                    return mergeFrom((HttpHealthCheck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(HttpHealthCheck httpHealthCheck) {
                if (httpHealthCheck == HttpHealthCheck.getDefaultInstance()) {
                    return this;
                }
                if (!httpHealthCheck.getHost().isEmpty()) {
                    this.host_ = httpHealthCheck.host_;
                    onChanged();
                }
                if (!httpHealthCheck.getPath().isEmpty()) {
                    this.path_ = httpHealthCheck.path_;
                    onChanged();
                }
                if (httpHealthCheck.hasSend()) {
                    mergeSend(httpHealthCheck.getSend());
                }
                if (httpHealthCheck.hasReceive()) {
                    mergeReceive(httpHealthCheck.getReceive());
                }
                if (this.requestHeadersToAddBuilder_ == null) {
                    if (!httpHealthCheck.requestHeadersToAdd_.isEmpty()) {
                        if (this.requestHeadersToAdd_.isEmpty()) {
                            this.requestHeadersToAdd_ = httpHealthCheck.requestHeadersToAdd_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureRequestHeadersToAddIsMutable();
                            this.requestHeadersToAdd_.addAll(httpHealthCheck.requestHeadersToAdd_);
                        }
                        onChanged();
                    }
                } else if (!httpHealthCheck.requestHeadersToAdd_.isEmpty()) {
                    if (!this.requestHeadersToAddBuilder_.isEmpty()) {
                        this.requestHeadersToAddBuilder_.addAllMessages(httpHealthCheck.requestHeadersToAdd_);
                    } else {
                        this.requestHeadersToAddBuilder_.dispose();
                        this.requestHeadersToAddBuilder_ = null;
                        this.requestHeadersToAdd_ = httpHealthCheck.requestHeadersToAdd_;
                        this.bitField0_ &= -2;
                        this.requestHeadersToAddBuilder_ = HttpHealthCheck.alwaysUseFieldBuilders ? getRequestHeadersToAddFieldBuilder() : null;
                    }
                }
                if (!httpHealthCheck.requestHeadersToRemove_.isEmpty()) {
                    if (this.requestHeadersToRemove_.isEmpty()) {
                        this.requestHeadersToRemove_ = httpHealthCheck.requestHeadersToRemove_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureRequestHeadersToRemoveIsMutable();
                        this.requestHeadersToRemove_.addAll(httpHealthCheck.requestHeadersToRemove_);
                    }
                    onChanged();
                }
                if (this.expectedStatusesBuilder_ == null) {
                    if (!httpHealthCheck.expectedStatuses_.isEmpty()) {
                        if (this.expectedStatuses_.isEmpty()) {
                            this.expectedStatuses_ = httpHealthCheck.expectedStatuses_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureExpectedStatusesIsMutable();
                            this.expectedStatuses_.addAll(httpHealthCheck.expectedStatuses_);
                        }
                        onChanged();
                    }
                } else if (!httpHealthCheck.expectedStatuses_.isEmpty()) {
                    if (!this.expectedStatusesBuilder_.isEmpty()) {
                        this.expectedStatusesBuilder_.addAllMessages(httpHealthCheck.expectedStatuses_);
                    } else {
                        this.expectedStatusesBuilder_.dispose();
                        this.expectedStatusesBuilder_ = null;
                        this.expectedStatuses_ = httpHealthCheck.expectedStatuses_;
                        this.bitField0_ &= -5;
                        this.expectedStatusesBuilder_ = HttpHealthCheck.alwaysUseFieldBuilders ? getExpectedStatusesFieldBuilder() : null;
                    }
                }
                if (httpHealthCheck.codecClientType_ != 0) {
                    setCodecClientTypeValue(httpHealthCheck.getCodecClientTypeValue());
                }
                if (httpHealthCheck.hasServiceNameMatcher()) {
                    mergeServiceNameMatcher(httpHealthCheck.getServiceNameMatcher());
                }
                m23030mergeUnknownFields(httpHealthCheck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck.Builder m23026mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck.access$2600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$HttpHealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$HttpHealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheck.Builder.m23026mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$HttpHealthCheck$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public String getHost() {
                Object obj = this.host_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.host_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setHost(String str) {
                str.getClass();
                this.host_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public ByteString getHostBytes() {
                Object obj = this.host_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.host_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setHostBytes(ByteString byteString) {
                byteString.getClass();
                HttpHealthCheck.checkByteStringIsUtf8(byteString);
                this.host_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearHost() {
                this.host_ = HttpHealthCheck.getDefaultInstance().getHost();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public String getPath() {
                Object obj = this.path_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.path_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setPath(String str) {
                str.getClass();
                this.path_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public ByteString getPathBytes() {
                Object obj = this.path_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.path_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setPathBytes(ByteString byteString) {
                byteString.getClass();
                HttpHealthCheck.checkByteStringIsUtf8(byteString);
                this.path_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearPath() {
                this.path_ = HttpHealthCheck.getDefaultInstance().getPath();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public Payload getSend() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Payload payload = this.send_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            public Builder setSend(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    payload.getClass();
                    this.send_ = payload;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(payload);
                }
                return this;
            }

            public Builder setSend(Payload.Builder builder) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.send_ = builder.m23048build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m23048build());
                }
                return this;
            }

            public Builder mergeSend(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Payload payload2 = this.send_;
                    if (payload2 != null) {
                        this.send_ = Payload.newBuilder(payload2).mergeFrom(payload).m23050buildPartial();
                    } else {
                        this.send_ = payload;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(payload);
                }
                return this;
            }

            public Builder clearSend() {
                if (this.sendBuilder_ == null) {
                    this.send_ = null;
                    onChanged();
                } else {
                    this.send_ = null;
                    this.sendBuilder_ = null;
                }
                return this;
            }

            public Payload.Builder getSendBuilder() {
                onChanged();
                return getSendFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public PayloadOrBuilder getSendOrBuilder() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (PayloadOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Payload payload = this.send_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> getSendFieldBuilder() {
                if (this.sendBuilder_ == null) {
                    this.sendBuilder_ = new SingleFieldBuilderV3<>(getSend(), getParentForChildren(), isClean());
                    this.send_ = null;
                }
                return this.sendBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public Payload getReceive() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.receiveBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Payload payload = this.receive_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            public Builder setReceive(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.receiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    payload.getClass();
                    this.receive_ = payload;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(payload);
                }
                return this;
            }

            public Builder setReceive(Payload.Builder builder) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.receiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.receive_ = builder.m23048build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m23048build());
                }
                return this;
            }

            public Builder mergeReceive(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.receiveBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Payload payload2 = this.receive_;
                    if (payload2 != null) {
                        this.receive_ = Payload.newBuilder(payload2).mergeFrom(payload).m23050buildPartial();
                    } else {
                        this.receive_ = payload;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(payload);
                }
                return this;
            }

            public Builder clearReceive() {
                if (this.receiveBuilder_ == null) {
                    this.receive_ = null;
                    onChanged();
                } else {
                    this.receive_ = null;
                    this.receiveBuilder_ = null;
                }
                return this;
            }

            public Payload.Builder getReceiveBuilder() {
                onChanged();
                return getReceiveFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public PayloadOrBuilder getReceiveOrBuilder() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.receiveBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (PayloadOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Payload payload = this.receive_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> getReceiveFieldBuilder() {
                if (this.receiveBuilder_ == null) {
                    this.receiveBuilder_ = new SingleFieldBuilderV3<>(getReceive(), getParentForChildren(), isClean());
                    this.receive_ = null;
                }
                return this.receiveBuilder_;
            }

            private void ensureRequestHeadersToAddIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.requestHeadersToAdd_ = new ArrayList(this.requestHeadersToAdd_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public List<HeaderValueOption> getRequestHeadersToAddList() {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.requestHeadersToAdd_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public int getRequestHeadersToAddCount() {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.requestHeadersToAdd_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public HeaderValueOption getRequestHeadersToAdd(int i) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.requestHeadersToAdd_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setRequestHeadersToAdd(int i, HeaderValueOption headerValueOption) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    headerValueOption.getClass();
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.set(i, headerValueOption);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, headerValueOption);
                }
                return this;
            }

            public Builder setRequestHeadersToAdd(int i, HeaderValueOption.Builder builder) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.set(i, builder.m22817build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m22817build());
                }
                return this;
            }

            public Builder addRequestHeadersToAdd(HeaderValueOption headerValueOption) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    headerValueOption.getClass();
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.add(headerValueOption);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(headerValueOption);
                }
                return this;
            }

            public Builder addRequestHeadersToAdd(int i, HeaderValueOption headerValueOption) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    headerValueOption.getClass();
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.add(i, headerValueOption);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, headerValueOption);
                }
                return this;
            }

            public Builder addRequestHeadersToAdd(HeaderValueOption.Builder builder) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.add(builder.m22817build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m22817build());
                }
                return this;
            }

            public Builder addRequestHeadersToAdd(int i, HeaderValueOption.Builder builder) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.add(i, builder.m22817build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m22817build());
                }
                return this;
            }

            public Builder addAllRequestHeadersToAdd(Iterable<? extends HeaderValueOption> iterable) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRequestHeadersToAddIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.requestHeadersToAdd_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearRequestHeadersToAdd() {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.requestHeadersToAdd_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeRequestHeadersToAdd(int i) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRequestHeadersToAddIsMutable();
                    this.requestHeadersToAdd_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public HeaderValueOption.Builder getRequestHeadersToAddBuilder(int i) {
                return getRequestHeadersToAddFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public HeaderValueOptionOrBuilder getRequestHeadersToAddOrBuilder(int i) {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.requestHeadersToAdd_.get(i);
                }
                return (HeaderValueOptionOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public List<? extends HeaderValueOptionOrBuilder> getRequestHeadersToAddOrBuilderList() {
                RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> repeatedFieldBuilderV3 = this.requestHeadersToAddBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.requestHeadersToAdd_);
            }

            public HeaderValueOption.Builder addRequestHeadersToAddBuilder() {
                return getRequestHeadersToAddFieldBuilder().addBuilder(HeaderValueOption.getDefaultInstance());
            }

            public HeaderValueOption.Builder addRequestHeadersToAddBuilder(int i) {
                return getRequestHeadersToAddFieldBuilder().addBuilder(i, HeaderValueOption.getDefaultInstance());
            }

            public List<HeaderValueOption.Builder> getRequestHeadersToAddBuilderList() {
                return getRequestHeadersToAddFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<HeaderValueOption, HeaderValueOption.Builder, HeaderValueOptionOrBuilder> getRequestHeadersToAddFieldBuilder() {
                if (this.requestHeadersToAddBuilder_ == null) {
                    this.requestHeadersToAddBuilder_ = new RepeatedFieldBuilderV3<>(this.requestHeadersToAdd_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.requestHeadersToAdd_ = null;
                }
                return this.requestHeadersToAddBuilder_;
            }

            private void ensureRequestHeadersToRemoveIsMutable() {
                if ((this.bitField0_ & 2) == 0) {
                    this.requestHeadersToRemove_ = new LazyStringArrayList(this.requestHeadersToRemove_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            /* renamed from: getRequestHeadersToRemoveList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo22993getRequestHeadersToRemoveList() {
                return this.requestHeadersToRemove_.getUnmodifiableView();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public int getRequestHeadersToRemoveCount() {
                return this.requestHeadersToRemove_.size();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public String getRequestHeadersToRemove(int i) {
                return (String) this.requestHeadersToRemove_.get(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public ByteString getRequestHeadersToRemoveBytes(int i) {
                return this.requestHeadersToRemove_.getByteString(i);
            }

            public Builder setRequestHeadersToRemove(int i, String str) {
                str.getClass();
                ensureRequestHeadersToRemoveIsMutable();
                this.requestHeadersToRemove_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addRequestHeadersToRemove(String str) {
                str.getClass();
                ensureRequestHeadersToRemoveIsMutable();
                this.requestHeadersToRemove_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllRequestHeadersToRemove(Iterable<String> iterable) {
                ensureRequestHeadersToRemoveIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.requestHeadersToRemove_);
                onChanged();
                return this;
            }

            public Builder clearRequestHeadersToRemove() {
                this.requestHeadersToRemove_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder addRequestHeadersToRemoveBytes(ByteString byteString) {
                byteString.getClass();
                HttpHealthCheck.checkByteStringIsUtf8(byteString);
                ensureRequestHeadersToRemoveIsMutable();
                this.requestHeadersToRemove_.add(byteString);
                onChanged();
                return this;
            }

            private void ensureExpectedStatusesIsMutable() {
                if ((this.bitField0_ & 4) == 0) {
                    this.expectedStatuses_ = new ArrayList(this.expectedStatuses_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public List<Int64Range> getExpectedStatusesList() {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.expectedStatuses_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public int getExpectedStatusesCount() {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.expectedStatuses_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public Int64Range getExpectedStatuses(int i) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.expectedStatuses_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setExpectedStatuses(int i, Int64Range int64Range) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    int64Range.getClass();
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.set(i, int64Range);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, int64Range);
                }
                return this;
            }

            public Builder setExpectedStatuses(int i, Int64Range.Builder builder) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.set(i, builder.m35121build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m35121build());
                }
                return this;
            }

            public Builder addExpectedStatuses(Int64Range int64Range) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    int64Range.getClass();
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.add(int64Range);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(int64Range);
                }
                return this;
            }

            public Builder addExpectedStatuses(int i, Int64Range int64Range) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    int64Range.getClass();
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.add(i, int64Range);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, int64Range);
                }
                return this;
            }

            public Builder addExpectedStatuses(Int64Range.Builder builder) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.add(builder.m35121build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m35121build());
                }
                return this;
            }

            public Builder addExpectedStatuses(int i, Int64Range.Builder builder) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.add(i, builder.m35121build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m35121build());
                }
                return this;
            }

            public Builder addAllExpectedStatuses(Iterable<? extends Int64Range> iterable) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureExpectedStatusesIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.expectedStatuses_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearExpectedStatuses() {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.expectedStatuses_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeExpectedStatuses(int i) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureExpectedStatusesIsMutable();
                    this.expectedStatuses_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Int64Range.Builder getExpectedStatusesBuilder(int i) {
                return getExpectedStatusesFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public Int64RangeOrBuilder getExpectedStatusesOrBuilder(int i) {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.expectedStatuses_.get(i);
                }
                return (Int64RangeOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public List<? extends Int64RangeOrBuilder> getExpectedStatusesOrBuilderList() {
                RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> repeatedFieldBuilderV3 = this.expectedStatusesBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.expectedStatuses_);
            }

            public Int64Range.Builder addExpectedStatusesBuilder() {
                return getExpectedStatusesFieldBuilder().addBuilder(Int64Range.getDefaultInstance());
            }

            public Int64Range.Builder addExpectedStatusesBuilder(int i) {
                return getExpectedStatusesFieldBuilder().addBuilder(i, Int64Range.getDefaultInstance());
            }

            public List<Int64Range.Builder> getExpectedStatusesBuilderList() {
                return getExpectedStatusesFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Int64Range, Int64Range.Builder, Int64RangeOrBuilder> getExpectedStatusesFieldBuilder() {
                if (this.expectedStatusesBuilder_ == null) {
                    this.expectedStatusesBuilder_ = new RepeatedFieldBuilderV3<>(this.expectedStatuses_, (this.bitField0_ & 4) != 0, getParentForChildren(), isClean());
                    this.expectedStatuses_ = null;
                }
                return this.expectedStatusesBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public CodecClientType getCodecClientType() {
                CodecClientType codecClientTypeValueOf = CodecClientType.valueOf(this.codecClientType_);
                return codecClientTypeValueOf == null ? CodecClientType.UNRECOGNIZED : codecClientTypeValueOf;
            }

            public Builder setCodecClientType(CodecClientType codecClientType) {
                codecClientType.getClass();
                this.codecClientType_ = codecClientType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearCodecClientType() {
                this.codecClientType_ = 0;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public StringMatcher getServiceNameMatcher() {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                StringMatcher stringMatcher = this.serviceNameMatcher_;
                return stringMatcher == null ? StringMatcher.getDefaultInstance() : stringMatcher;
            }

            public Builder setServiceNameMatcher(StringMatcher stringMatcher) {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV3 == null) {
                    stringMatcher.getClass();
                    this.serviceNameMatcher_ = stringMatcher;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(stringMatcher);
                }
                return this;
            }

            public Builder setServiceNameMatcher(StringMatcher.Builder builder) {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.serviceNameMatcher_ = builder.m33831build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m33831build());
                }
                return this;
            }

            public Builder mergeServiceNameMatcher(StringMatcher stringMatcher) {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV3 == null) {
                    StringMatcher stringMatcher2 = this.serviceNameMatcher_;
                    if (stringMatcher2 != null) {
                        this.serviceNameMatcher_ = StringMatcher.newBuilder(stringMatcher2).mergeFrom(stringMatcher).m33833buildPartial();
                    } else {
                        this.serviceNameMatcher_ = stringMatcher;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(stringMatcher);
                }
                return this;
            }

            public Builder clearServiceNameMatcher() {
                if (this.serviceNameMatcherBuilder_ == null) {
                    this.serviceNameMatcher_ = null;
                    onChanged();
                } else {
                    this.serviceNameMatcher_ = null;
                    this.serviceNameMatcherBuilder_ = null;
                }
                return this;
            }

            public StringMatcher.Builder getServiceNameMatcherBuilder() {
                onChanged();
                return getServiceNameMatcherFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.HttpHealthCheckOrBuilder
            public StringMatcherOrBuilder getServiceNameMatcherOrBuilder() {
                SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> singleFieldBuilderV3 = this.serviceNameMatcherBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (StringMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                StringMatcher stringMatcher = this.serviceNameMatcher_;
                return stringMatcher == null ? StringMatcher.getDefaultInstance() : stringMatcher;
            }

            private SingleFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> getServiceNameMatcherFieldBuilder() {
                if (this.serviceNameMatcherBuilder_ == null) {
                    this.serviceNameMatcherBuilder_ = new SingleFieldBuilderV3<>(getServiceNameMatcher(), getParentForChildren(), isClean());
                    this.serviceNameMatcher_ = null;
                }
                return this.serviceNameMatcherBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23036setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23030mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class TcpHealthCheck extends GeneratedMessageV3 implements TcpHealthCheckOrBuilder {
        public static final int RECEIVE_FIELD_NUMBER = 2;
        public static final int SEND_FIELD_NUMBER = 1;
        private static final TcpHealthCheck DEFAULT_INSTANCE = new TcpHealthCheck();
        private static final Parser<TcpHealthCheck> PARSER = new AbstractParser<TcpHealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public TcpHealthCheck m23137parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TcpHealthCheck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<Payload> receive_;
        private Payload send_;

        private TcpHealthCheck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TcpHealthCheck() {
            this.memoizedIsInitialized = (byte) -1;
            this.receive_ = Collections.emptyList();
        }

        private TcpHealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Payload payload = this.send_;
                                    Payload.Builder builderM23043toBuilder = payload != null ? payload.m23043toBuilder() : null;
                                    Payload payload2 = (Payload) codedInputStream.readMessage(Payload.parser(), extensionRegistryLite);
                                    this.send_ = payload2;
                                    if (builderM23043toBuilder != null) {
                                        builderM23043toBuilder.mergeFrom(payload2);
                                        this.send_ = builderM23043toBuilder.m23050buildPartial();
                                    }
                                } else if (tag == 18) {
                                    if (!(z2 & true)) {
                                        this.receive_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.receive_.add(codedInputStream.readMessage(Payload.parser(), extensionRegistryLite));
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
                    if (z2 & true) {
                        this.receive_ = Collections.unmodifiableList(this.receive_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static TcpHealthCheck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TcpHealthCheck> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TcpHealthCheck_descriptor;
        }

        public static TcpHealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(byteBuffer);
        }

        public static TcpHealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TcpHealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(byteString);
        }

        public static TcpHealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TcpHealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(bArr);
        }

        public static TcpHealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TcpHealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TcpHealthCheck parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TcpHealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TcpHealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TcpHealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TcpHealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TcpHealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m23135toBuilder();
        }

        public static Builder newBuilder(TcpHealthCheck tcpHealthCheck) {
            return DEFAULT_INSTANCE.m23135toBuilder().mergeFrom(tcpHealthCheck);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TcpHealthCheck m23130getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<TcpHealthCheck> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public List<Payload> getReceiveList() {
            return this.receive_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public List<? extends PayloadOrBuilder> getReceiveOrBuilderList() {
            return this.receive_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public boolean hasSend() {
            return this.send_ != null;
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
            return new TcpHealthCheck();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TcpHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(TcpHealthCheck.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public Payload getSend() {
            Payload payload = this.send_;
            return payload == null ? Payload.getDefaultInstance() : payload;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public PayloadOrBuilder getSendOrBuilder() {
            return getSend();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public int getReceiveCount() {
            return this.receive_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public Payload getReceive(int i) {
            return this.receive_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
        public PayloadOrBuilder getReceiveOrBuilder(int i) {
            return this.receive_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.send_ != null) {
                codedOutputStream.writeMessage(1, getSend());
            }
            for (int i = 0; i < this.receive_.size(); i++) {
                codedOutputStream.writeMessage(2, this.receive_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.send_ != null ? CodedOutputStream.computeMessageSize(1, getSend()) : 0;
            for (int i2 = 0; i2 < this.receive_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.receive_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TcpHealthCheck)) {
                return super.equals(obj);
            }
            TcpHealthCheck tcpHealthCheck = (TcpHealthCheck) obj;
            if (hasSend() != tcpHealthCheck.hasSend()) {
                return false;
            }
            return (!hasSend() || getSend().equals(tcpHealthCheck.getSend())) && getReceiveList().equals(tcpHealthCheck.getReceiveList()) && this.unknownFields.equals(tcpHealthCheck.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasSend()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getSend().hashCode();
            }
            if (getReceiveCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getReceiveList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23132newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23135toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TcpHealthCheckOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> receiveBuilder_;
            private List<Payload> receive_;
            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> sendBuilder_;
            private Payload send_;

            private Builder() {
                this.receive_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.receive_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TcpHealthCheck_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public boolean hasSend() {
                return (this.sendBuilder_ == null && this.send_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TcpHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(TcpHealthCheck.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (TcpHealthCheck.alwaysUseFieldBuilders) {
                    getReceiveFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23146clear() {
                super.clear();
                if (this.sendBuilder_ == null) {
                    this.send_ = null;
                } else {
                    this.send_ = null;
                    this.sendBuilder_ = null;
                }
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.receive_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TcpHealthCheck_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TcpHealthCheck m23159getDefaultInstanceForType() {
                return TcpHealthCheck.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TcpHealthCheck m23140build() throws UninitializedMessageException {
                TcpHealthCheck tcpHealthCheckM23142buildPartial = m23142buildPartial();
                if (tcpHealthCheckM23142buildPartial.isInitialized()) {
                    return tcpHealthCheckM23142buildPartial;
                }
                throw newUninitializedMessageException(tcpHealthCheckM23142buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TcpHealthCheck m23142buildPartial() {
                TcpHealthCheck tcpHealthCheck = new TcpHealthCheck(this);
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    tcpHealthCheck.send_ = this.send_;
                } else {
                    tcpHealthCheck.send_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.receive_ = Collections.unmodifiableList(this.receive_);
                        this.bitField0_ &= -2;
                    }
                    tcpHealthCheck.receive_ = this.receive_;
                } else {
                    tcpHealthCheck.receive_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return tcpHealthCheck;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23158clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23170setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23148clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23151clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23172setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23138addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23163mergeFrom(Message message) {
                if (message instanceof TcpHealthCheck) {
                    return mergeFrom((TcpHealthCheck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TcpHealthCheck tcpHealthCheck) {
                if (tcpHealthCheck == TcpHealthCheck.getDefaultInstance()) {
                    return this;
                }
                if (tcpHealthCheck.hasSend()) {
                    mergeSend(tcpHealthCheck.getSend());
                }
                if (this.receiveBuilder_ == null) {
                    if (!tcpHealthCheck.receive_.isEmpty()) {
                        if (this.receive_.isEmpty()) {
                            this.receive_ = tcpHealthCheck.receive_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureReceiveIsMutable();
                            this.receive_.addAll(tcpHealthCheck.receive_);
                        }
                        onChanged();
                    }
                } else if (!tcpHealthCheck.receive_.isEmpty()) {
                    if (!this.receiveBuilder_.isEmpty()) {
                        this.receiveBuilder_.addAllMessages(tcpHealthCheck.receive_);
                    } else {
                        this.receiveBuilder_.dispose();
                        this.receiveBuilder_ = null;
                        this.receive_ = tcpHealthCheck.receive_;
                        this.bitField0_ &= -2;
                        this.receiveBuilder_ = TcpHealthCheck.alwaysUseFieldBuilders ? getReceiveFieldBuilder() : null;
                    }
                }
                m23168mergeUnknownFields(tcpHealthCheck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck.Builder m23164mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck.access$3900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TcpHealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TcpHealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheck.Builder.m23164mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TcpHealthCheck$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public Payload getSend() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Payload payload = this.send_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            public Builder setSend(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    payload.getClass();
                    this.send_ = payload;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(payload);
                }
                return this;
            }

            public Builder setSend(Payload.Builder builder) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.send_ = builder.m23048build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m23048build());
                }
                return this;
            }

            public Builder mergeSend(Payload payload) {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Payload payload2 = this.send_;
                    if (payload2 != null) {
                        this.send_ = Payload.newBuilder(payload2).mergeFrom(payload).m23050buildPartial();
                    } else {
                        this.send_ = payload;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(payload);
                }
                return this;
            }

            public Builder clearSend() {
                if (this.sendBuilder_ == null) {
                    this.send_ = null;
                    onChanged();
                } else {
                    this.send_ = null;
                    this.sendBuilder_ = null;
                }
                return this;
            }

            public Payload.Builder getSendBuilder() {
                onChanged();
                return getSendFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public PayloadOrBuilder getSendOrBuilder() {
                SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> singleFieldBuilderV3 = this.sendBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (PayloadOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Payload payload = this.send_;
                return payload == null ? Payload.getDefaultInstance() : payload;
            }

            private SingleFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> getSendFieldBuilder() {
                if (this.sendBuilder_ == null) {
                    this.sendBuilder_ = new SingleFieldBuilderV3<>(getSend(), getParentForChildren(), isClean());
                    this.send_ = null;
                }
                return this.sendBuilder_;
            }

            private void ensureReceiveIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.receive_ = new ArrayList(this.receive_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public List<Payload> getReceiveList() {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.receive_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public int getReceiveCount() {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receive_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public Payload getReceive(int i) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receive_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setReceive(int i, Payload payload) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    payload.getClass();
                    ensureReceiveIsMutable();
                    this.receive_.set(i, payload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, payload);
                }
                return this;
            }

            public Builder setReceive(int i, Payload.Builder builder) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveIsMutable();
                    this.receive_.set(i, builder.m23048build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m23048build());
                }
                return this;
            }

            public Builder addReceive(Payload payload) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    payload.getClass();
                    ensureReceiveIsMutable();
                    this.receive_.add(payload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(payload);
                }
                return this;
            }

            public Builder addReceive(int i, Payload payload) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    payload.getClass();
                    ensureReceiveIsMutable();
                    this.receive_.add(i, payload);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, payload);
                }
                return this;
            }

            public Builder addReceive(Payload.Builder builder) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveIsMutable();
                    this.receive_.add(builder.m23048build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m23048build());
                }
                return this;
            }

            public Builder addReceive(int i, Payload.Builder builder) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveIsMutable();
                    this.receive_.add(i, builder.m23048build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m23048build());
                }
                return this;
            }

            public Builder addAllReceive(Iterable<? extends Payload> iterable) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.receive_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearReceive() {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.receive_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeReceive(int i) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureReceiveIsMutable();
                    this.receive_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Payload.Builder getReceiveBuilder(int i) {
                return getReceiveFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public PayloadOrBuilder getReceiveOrBuilder(int i) {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.receive_.get(i);
                }
                return (PayloadOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TcpHealthCheckOrBuilder
            public List<? extends PayloadOrBuilder> getReceiveOrBuilderList() {
                RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> repeatedFieldBuilderV3 = this.receiveBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.receive_);
            }

            public Payload.Builder addReceiveBuilder() {
                return getReceiveFieldBuilder().addBuilder(Payload.getDefaultInstance());
            }

            public Payload.Builder addReceiveBuilder(int i) {
                return getReceiveFieldBuilder().addBuilder(i, Payload.getDefaultInstance());
            }

            public List<Payload.Builder> getReceiveBuilderList() {
                return getReceiveFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Payload, Payload.Builder, PayloadOrBuilder> getReceiveFieldBuilder() {
                if (this.receiveBuilder_ == null) {
                    this.receiveBuilder_ = new RepeatedFieldBuilderV3<>(this.receive_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.receive_ = null;
                }
                return this.receiveBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23174setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23168mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class RedisHealthCheck extends GeneratedMessageV3 implements RedisHealthCheckOrBuilder {
        public static final int KEY_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final RedisHealthCheck DEFAULT_INSTANCE = new RedisHealthCheck();
        private static final Parser<RedisHealthCheck> PARSER = new AbstractParser<RedisHealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public RedisHealthCheck m23091parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RedisHealthCheck(codedInputStream, extensionRegistryLite);
            }
        };
        private volatile Object key_;
        private byte memoizedIsInitialized;

        private RedisHealthCheck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RedisHealthCheck() {
            this.memoizedIsInitialized = (byte) -1;
            this.key_ = "";
        }

        private RedisHealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.key_ = codedInputStream.readStringRequireUtf8();
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

        public static RedisHealthCheck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RedisHealthCheck> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_RedisHealthCheck_descriptor;
        }

        public static RedisHealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(byteBuffer);
        }

        public static RedisHealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RedisHealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(byteString);
        }

        public static RedisHealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RedisHealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(bArr);
        }

        public static RedisHealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RedisHealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RedisHealthCheck parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RedisHealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RedisHealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RedisHealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RedisHealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RedisHealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m23089toBuilder();
        }

        public static Builder newBuilder(RedisHealthCheck redisHealthCheck) {
            return DEFAULT_INSTANCE.m23089toBuilder().mergeFrom(redisHealthCheck);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RedisHealthCheck m23084getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<RedisHealthCheck> getParserForType() {
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
            return new RedisHealthCheck();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_RedisHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(RedisHealthCheck.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheckOrBuilder
        public String getKey() {
            Object obj = this.key_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.key_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheckOrBuilder
        public ByteString getKeyBytes() {
            Object obj = this.key_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.key_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getKeyBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.key_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RedisHealthCheck)) {
                return super.equals(obj);
            }
            RedisHealthCheck redisHealthCheck = (RedisHealthCheck) obj;
            return getKey().equals(redisHealthCheck.getKey()) && this.unknownFields.equals(redisHealthCheck.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getKey().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23086newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23089toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RedisHealthCheckOrBuilder {
            private Object key_;

            private Builder() {
                this.key_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.key_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_RedisHealthCheck_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_RedisHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(RedisHealthCheck.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RedisHealthCheck.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23100clear() {
                super.clear();
                this.key_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_RedisHealthCheck_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RedisHealthCheck m23113getDefaultInstanceForType() {
                return RedisHealthCheck.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RedisHealthCheck m23094build() throws UninitializedMessageException {
                RedisHealthCheck redisHealthCheckM23096buildPartial = m23096buildPartial();
                if (redisHealthCheckM23096buildPartial.isInitialized()) {
                    return redisHealthCheckM23096buildPartial;
                }
                throw newUninitializedMessageException(redisHealthCheckM23096buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public RedisHealthCheck m23096buildPartial() {
                RedisHealthCheck redisHealthCheck = new RedisHealthCheck(this);
                redisHealthCheck.key_ = this.key_;
                onBuilt();
                return redisHealthCheck;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23112clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23124setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23102clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23105clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23126setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23092addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23117mergeFrom(Message message) {
                if (message instanceof RedisHealthCheck) {
                    return mergeFrom((RedisHealthCheck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RedisHealthCheck redisHealthCheck) {
                if (redisHealthCheck == RedisHealthCheck.getDefaultInstance()) {
                    return this;
                }
                if (!redisHealthCheck.getKey().isEmpty()) {
                    this.key_ = redisHealthCheck.key_;
                    onChanged();
                }
                m23122mergeUnknownFields(redisHealthCheck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck.Builder m23118mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck.access$4700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$RedisHealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$RedisHealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheck.Builder.m23118mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$RedisHealthCheck$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheckOrBuilder
            public String getKey() {
                Object obj = this.key_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.key_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setKey(String str) {
                str.getClass();
                this.key_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.RedisHealthCheckOrBuilder
            public ByteString getKeyBytes() {
                Object obj = this.key_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.key_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setKeyBytes(ByteString byteString) {
                byteString.getClass();
                RedisHealthCheck.checkByteStringIsUtf8(byteString);
                this.key_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearKey() {
                this.key_ = RedisHealthCheck.getDefaultInstance().getKey();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23128setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23122mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class GrpcHealthCheck extends GeneratedMessageV3 implements GrpcHealthCheckOrBuilder {
        public static final int AUTHORITY_FIELD_NUMBER = 2;
        public static final int SERVICE_NAME_FIELD_NUMBER = 1;
        private static final GrpcHealthCheck DEFAULT_INSTANCE = new GrpcHealthCheck();
        private static final Parser<GrpcHealthCheck> PARSER = new AbstractParser<GrpcHealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public GrpcHealthCheck m22952parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GrpcHealthCheck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object authority_;
        private byte memoizedIsInitialized;
        private volatile Object serviceName_;

        private GrpcHealthCheck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GrpcHealthCheck() {
            this.memoizedIsInitialized = (byte) -1;
            this.serviceName_ = "";
            this.authority_ = "";
        }

        private GrpcHealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.serviceName_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.authority_ = codedInputStream.readStringRequireUtf8();
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

        public static GrpcHealthCheck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GrpcHealthCheck> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_GrpcHealthCheck_descriptor;
        }

        public static GrpcHealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(byteBuffer);
        }

        public static GrpcHealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GrpcHealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(byteString);
        }

        public static GrpcHealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GrpcHealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(bArr);
        }

        public static GrpcHealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GrpcHealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GrpcHealthCheck parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GrpcHealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GrpcHealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GrpcHealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GrpcHealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GrpcHealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m22950toBuilder();
        }

        public static Builder newBuilder(GrpcHealthCheck grpcHealthCheck) {
            return DEFAULT_INSTANCE.m22950toBuilder().mergeFrom(grpcHealthCheck);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GrpcHealthCheck m22945getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<GrpcHealthCheck> getParserForType() {
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
            return new GrpcHealthCheck();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_GrpcHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcHealthCheck.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
        public String getServiceName() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.serviceName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
        public ByteString getServiceNameBytes() {
            Object obj = this.serviceName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.serviceName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
        public String getAuthority() {
            Object obj = this.authority_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.authority_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
        public ByteString getAuthorityBytes() {
            Object obj = this.authority_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.authority_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getServiceNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.serviceName_);
            }
            if (!getAuthorityBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.authority_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getServiceNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.serviceName_) : 0;
            if (!getAuthorityBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.authority_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GrpcHealthCheck)) {
                return super.equals(obj);
            }
            GrpcHealthCheck grpcHealthCheck = (GrpcHealthCheck) obj;
            return getServiceName().equals(grpcHealthCheck.getServiceName()) && getAuthority().equals(grpcHealthCheck.getAuthority()) && this.unknownFields.equals(grpcHealthCheck.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getServiceName().hashCode()) * 37) + 2) * 53) + getAuthority().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22947newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22950toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GrpcHealthCheckOrBuilder {
            private Object authority_;
            private Object serviceName_;

            private Builder() {
                this.serviceName_ = "";
                this.authority_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.serviceName_ = "";
                this.authority_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_GrpcHealthCheck_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_GrpcHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcHealthCheck.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GrpcHealthCheck.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22961clear() {
                super.clear();
                this.serviceName_ = "";
                this.authority_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_GrpcHealthCheck_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcHealthCheck m22974getDefaultInstanceForType() {
                return GrpcHealthCheck.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcHealthCheck m22955build() throws UninitializedMessageException {
                GrpcHealthCheck grpcHealthCheckM22957buildPartial = m22957buildPartial();
                if (grpcHealthCheckM22957buildPartial.isInitialized()) {
                    return grpcHealthCheckM22957buildPartial;
                }
                throw newUninitializedMessageException(grpcHealthCheckM22957buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GrpcHealthCheck m22957buildPartial() {
                GrpcHealthCheck grpcHealthCheck = new GrpcHealthCheck(this);
                grpcHealthCheck.serviceName_ = this.serviceName_;
                grpcHealthCheck.authority_ = this.authority_;
                onBuilt();
                return grpcHealthCheck;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22973clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22985setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22963clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22966clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22987setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22953addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22978mergeFrom(Message message) {
                if (message instanceof GrpcHealthCheck) {
                    return mergeFrom((GrpcHealthCheck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GrpcHealthCheck grpcHealthCheck) {
                if (grpcHealthCheck == GrpcHealthCheck.getDefaultInstance()) {
                    return this;
                }
                if (!grpcHealthCheck.getServiceName().isEmpty()) {
                    this.serviceName_ = grpcHealthCheck.serviceName_;
                    onChanged();
                }
                if (!grpcHealthCheck.getAuthority().isEmpty()) {
                    this.authority_ = grpcHealthCheck.authority_;
                    onChanged();
                }
                m22983mergeUnknownFields(grpcHealthCheck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck.Builder m22979mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck.access$5700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$GrpcHealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$GrpcHealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheck.Builder.m22979mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$GrpcHealthCheck$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
            public String getServiceName() {
                Object obj = this.serviceName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.serviceName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setServiceName(String str) {
                str.getClass();
                this.serviceName_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
            public ByteString getServiceNameBytes() {
                Object obj = this.serviceName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.serviceName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setServiceNameBytes(ByteString byteString) {
                byteString.getClass();
                GrpcHealthCheck.checkByteStringIsUtf8(byteString);
                this.serviceName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearServiceName() {
                this.serviceName_ = GrpcHealthCheck.getDefaultInstance().getServiceName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
            public String getAuthority() {
                Object obj = this.authority_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.authority_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setAuthority(String str) {
                str.getClass();
                this.authority_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.GrpcHealthCheckOrBuilder
            public ByteString getAuthorityBytes() {
                Object obj = this.authority_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.authority_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAuthorityBytes(ByteString byteString) {
                byteString.getClass();
                GrpcHealthCheck.checkByteStringIsUtf8(byteString);
                this.authority_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearAuthority() {
                this.authority_ = GrpcHealthCheck.getDefaultInstance().getAuthority();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m22989setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m22983mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class CustomHealthCheck extends GeneratedMessageV3 implements CustomHealthCheckOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private static final CustomHealthCheck DEFAULT_INSTANCE = new CustomHealthCheck();
        private static final Parser<CustomHealthCheck> PARSER = new AbstractParser<CustomHealthCheck>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CustomHealthCheck m22906parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CustomHealthCheck(codedInputStream, extensionRegistryLite);
            }
        };
        private int configTypeCase_;
        private Object configType_;
        private byte memoizedIsInitialized;
        private volatile Object name_;

        private CustomHealthCheck(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private CustomHealthCheck() {
            this.configTypeCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
        }

        private CustomHealthCheck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (tag == 26) {
                                Any.Builder builder = this.configTypeCase_ == 3 ? ((Any) this.configType_).toBuilder() : null;
                                Any message = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                this.configType_ = message;
                                if (builder != null) {
                                    builder.mergeFrom(message);
                                    this.configType_ = builder.buildPartial();
                                }
                                this.configTypeCase_ = 3;
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

        public static CustomHealthCheck getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CustomHealthCheck> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_CustomHealthCheck_descriptor;
        }

        public static CustomHealthCheck parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(byteBuffer);
        }

        public static CustomHealthCheck parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CustomHealthCheck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(byteString);
        }

        public static CustomHealthCheck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CustomHealthCheck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(bArr);
        }

        public static CustomHealthCheck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CustomHealthCheck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CustomHealthCheck parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CustomHealthCheck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CustomHealthCheck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CustomHealthCheck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CustomHealthCheck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CustomHealthCheck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m22904toBuilder();
        }

        public static Builder newBuilder(CustomHealthCheck customHealthCheck) {
            return DEFAULT_INSTANCE.m22904toBuilder().mergeFrom(customHealthCheck);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CustomHealthCheck m22899getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<CustomHealthCheck> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public boolean hasTypedConfig() {
            return this.configTypeCase_ == 3;
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
            return new CustomHealthCheck();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_CustomHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHealthCheck.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public ConfigTypeCase getConfigTypeCase() {
            return ConfigTypeCase.forNumber(this.configTypeCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public Any getTypedConfig() {
            if (this.configTypeCase_ == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
        public AnyOrBuilder getTypedConfigOrBuilder() {
            if (this.configTypeCase_ == 3) {
                return (Any) this.configType_;
            }
            return Any.getDefaultInstance();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (this.configTypeCase_ == 3) {
                codedOutputStream.writeMessage(3, (Any) this.configType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if (this.configTypeCase_ == 3) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, (Any) this.configType_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CustomHealthCheck)) {
                return super.equals(obj);
            }
            CustomHealthCheck customHealthCheck = (CustomHealthCheck) obj;
            if (getName().equals(customHealthCheck.getName()) && getConfigTypeCase().equals(customHealthCheck.getConfigTypeCase())) {
                return (this.configTypeCase_ != 3 || getTypedConfig().equals(customHealthCheck.getTypedConfig())) && this.unknownFields.equals(customHealthCheck.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
            if (this.configTypeCase_ == 3) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getTypedConfig().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22901newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22904toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            TYPED_CONFIG(3),
            CONFIGTYPE_NOT_SET(0);

            private final int value;

            ConfigTypeCase(int i) {
                this.value = i;
            }

            public static ConfigTypeCase forNumber(int i) {
                if (i == 0) {
                    return CONFIGTYPE_NOT_SET;
                }
                if (i != 3) {
                    return null;
                }
                return TYPED_CONFIG;
            }

            @Deprecated
            public static ConfigTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CustomHealthCheckOrBuilder {
            private int configTypeCase_;
            private Object configType_;
            private Object name_;
            private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;

            private Builder() {
                this.configTypeCase_ = 0;
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.configTypeCase_ = 0;
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_CustomHealthCheck_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
            public boolean hasTypedConfig() {
                return this.configTypeCase_ == 3;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_CustomHealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(CustomHealthCheck.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CustomHealthCheck.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22915clear() {
                super.clear();
                this.name_ = "";
                this.configTypeCase_ = 0;
                this.configType_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_CustomHealthCheck_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CustomHealthCheck m22928getDefaultInstanceForType() {
                return CustomHealthCheck.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CustomHealthCheck m22909build() throws UninitializedMessageException {
                CustomHealthCheck customHealthCheckM22911buildPartial = m22911buildPartial();
                if (customHealthCheckM22911buildPartial.isInitialized()) {
                    return customHealthCheckM22911buildPartial;
                }
                throw newUninitializedMessageException(customHealthCheckM22911buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CustomHealthCheck m22911buildPartial() {
                CustomHealthCheck customHealthCheck = new CustomHealthCheck(this);
                customHealthCheck.name_ = this.name_;
                if (this.configTypeCase_ == 3) {
                    SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        customHealthCheck.configType_ = this.configType_;
                    } else {
                        customHealthCheck.configType_ = singleFieldBuilderV3.build();
                    }
                }
                customHealthCheck.configTypeCase_ = this.configTypeCase_;
                onBuilt();
                return customHealthCheck;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22927clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22939setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22917clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22920clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22941setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22907addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m22932mergeFrom(Message message) {
                if (message instanceof CustomHealthCheck) {
                    return mergeFrom((CustomHealthCheck) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CustomHealthCheck customHealthCheck) {
                if (customHealthCheck == CustomHealthCheck.getDefaultInstance()) {
                    return this;
                }
                if (!customHealthCheck.getName().isEmpty()) {
                    this.name_ = customHealthCheck.name_;
                    onChanged();
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$CustomHealthCheck$ConfigTypeCase[customHealthCheck.getConfigTypeCase().ordinal()] == 1) {
                    mergeTypedConfig(customHealthCheck.getTypedConfig());
                }
                m22937mergeUnknownFields(customHealthCheck.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck.Builder m22933mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck.access$6900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$CustomHealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$CustomHealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheck.Builder.m22933mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$CustomHealthCheck$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
            public ConfigTypeCase getConfigTypeCase() {
                return ConfigTypeCase.forNumber(this.configTypeCase_);
            }

            public Builder clearConfigType() {
                this.configTypeCase_ = 0;
                this.configType_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
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

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
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
                CustomHealthCheck.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = CustomHealthCheck.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
            public Any getTypedConfig() {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.configTypeCase_ == 3) {
                        return (Any) this.configType_;
                    }
                    return Any.getDefaultInstance();
                }
                if (this.configTypeCase_ == 3) {
                    return singleFieldBuilderV3.getMessage();
                }
                return Any.getDefaultInstance();
            }

            public Builder setTypedConfig(Any any) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    any.getClass();
                    this.configType_ = any;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(any);
                }
                this.configTypeCase_ = 3;
                return this;
            }

            public Builder setTypedConfig(Any.Builder builder) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.configType_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                this.configTypeCase_ = 3;
                return this;
            }

            public Builder mergeTypedConfig(Any any) {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if (this.configTypeCase_ != 3 || this.configType_ == Any.getDefaultInstance()) {
                        this.configType_ = any;
                    } else {
                        this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                    }
                    onChanged();
                } else {
                    if (this.configTypeCase_ == 3) {
                        singleFieldBuilderV3.mergeFrom(any);
                    }
                    this.typedConfigBuilder_.setMessage(any);
                }
                this.configTypeCase_ = 3;
                return this;
            }

            public Builder clearTypedConfig() {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                if (singleFieldBuilderV3 != null) {
                    if (this.configTypeCase_ == 3) {
                        this.configTypeCase_ = 0;
                        this.configType_ = null;
                    }
                    singleFieldBuilderV3.clear();
                } else if (this.configTypeCase_ == 3) {
                    this.configTypeCase_ = 0;
                    this.configType_ = null;
                    onChanged();
                }
                return this;
            }

            public Any.Builder getTypedConfigBuilder() {
                return getTypedConfigFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.CustomHealthCheckOrBuilder
            public AnyOrBuilder getTypedConfigOrBuilder() {
                SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
                int i = this.configTypeCase_;
                if (i == 3 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                if (i == 3) {
                    return (Any) this.configType_;
                }
                return Any.getDefaultInstance();
            }

            private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
                if (this.typedConfigBuilder_ == null) {
                    if (this.configTypeCase_ != 3) {
                        this.configType_ = Any.getDefaultInstance();
                    }
                    this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                    this.configType_ = null;
                }
                this.configTypeCase_ = 3;
                onChanged();
                return this.typedConfigBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m22943setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m22937mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class TlsOptions extends GeneratedMessageV3 implements TlsOptionsOrBuilder {
        public static final int ALPN_PROTOCOLS_FIELD_NUMBER = 1;
        private static final TlsOptions DEFAULT_INSTANCE = new TlsOptions();
        private static final Parser<TlsOptions> PARSER = new AbstractParser<TlsOptions>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public TlsOptions m23184parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TlsOptions(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private LazyStringList alpnProtocols_;
        private byte memoizedIsInitialized;

        private TlsOptions(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private TlsOptions() {
            this.memoizedIsInitialized = (byte) -1;
            this.alpnProtocols_ = LazyStringArrayList.EMPTY;
        }

        private TlsOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.alpnProtocols_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.alpnProtocols_.add(stringRequireUtf8);
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
                        this.alpnProtocols_ = this.alpnProtocols_.getUnmodifiableView();
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static TlsOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TlsOptions> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TlsOptions_descriptor;
        }

        public static TlsOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(byteBuffer);
        }

        public static TlsOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static TlsOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(byteString);
        }

        public static TlsOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TlsOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(bArr);
        }

        public static TlsOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TlsOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TlsOptions parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static TlsOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TlsOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static TlsOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static TlsOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static TlsOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m23182toBuilder();
        }

        public static Builder newBuilder(TlsOptions tlsOptions) {
            return DEFAULT_INSTANCE.m23182toBuilder().mergeFrom(tlsOptions);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
        /* renamed from: getAlpnProtocolsList, reason: merged with bridge method [inline-methods] */
        public ProtocolStringList mo23176getAlpnProtocolsList() {
            return this.alpnProtocols_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsOptions m23177getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<TlsOptions> getParserForType() {
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
            return new TlsOptions();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TlsOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsOptions.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
        public int getAlpnProtocolsCount() {
            return this.alpnProtocols_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
        public String getAlpnProtocols(int i) {
            return (String) this.alpnProtocols_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
        public ByteString getAlpnProtocolsBytes(int i) {
            return this.alpnProtocols_.getByteString(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.alpnProtocols_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.alpnProtocols_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSizeNoTag = 0;
            for (int i2 = 0; i2 < this.alpnProtocols_.size(); i2++) {
                iComputeStringSizeNoTag += computeStringSizeNoTag(this.alpnProtocols_.getRaw(i2));
            }
            int size = iComputeStringSizeNoTag + mo23176getAlpnProtocolsList().size() + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TlsOptions)) {
                return super.equals(obj);
            }
            TlsOptions tlsOptions = (TlsOptions) obj;
            return mo23176getAlpnProtocolsList().equals(tlsOptions.mo23176getAlpnProtocolsList()) && this.unknownFields.equals(tlsOptions.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getAlpnProtocolsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + mo23176getAlpnProtocolsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23179newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m23182toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TlsOptionsOrBuilder {
            private LazyStringList alpnProtocols_;
            private int bitField0_;

            private Builder() {
                this.alpnProtocols_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.alpnProtocols_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TlsOptions_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TlsOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsOptions.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = TlsOptions.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23193clear() {
                super.clear();
                this.alpnProtocols_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_TlsOptions_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsOptions m23206getDefaultInstanceForType() {
                return TlsOptions.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsOptions m23187build() throws UninitializedMessageException {
                TlsOptions tlsOptionsM23189buildPartial = m23189buildPartial();
                if (tlsOptionsM23189buildPartial.isInitialized()) {
                    return tlsOptionsM23189buildPartial;
                }
                throw newUninitializedMessageException(tlsOptionsM23189buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public TlsOptions m23189buildPartial() {
                TlsOptions tlsOptions = new TlsOptions(this);
                if ((this.bitField0_ & 1) != 0) {
                    this.alpnProtocols_ = this.alpnProtocols_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                tlsOptions.alpnProtocols_ = this.alpnProtocols_;
                onBuilt();
                return tlsOptions;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23205clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23217setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23195clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23198clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23219setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23185addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m23210mergeFrom(Message message) {
                if (message instanceof TlsOptions) {
                    return mergeFrom((TlsOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TlsOptions tlsOptions) {
                if (tlsOptions == TlsOptions.getDefaultInstance()) {
                    return this;
                }
                if (!tlsOptions.alpnProtocols_.isEmpty()) {
                    if (this.alpnProtocols_.isEmpty()) {
                        this.alpnProtocols_ = tlsOptions.alpnProtocols_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAlpnProtocolsIsMutable();
                        this.alpnProtocols_.addAll(tlsOptions.alpnProtocols_);
                    }
                    onChanged();
                }
                m23215mergeUnknownFields(tlsOptions.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions.Builder m23211mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions.access$7800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TlsOptions r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TlsOptions r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptions.Builder.m23211mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$TlsOptions$Builder");
            }

            private void ensureAlpnProtocolsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.alpnProtocols_ = new LazyStringArrayList(this.alpnProtocols_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
            /* renamed from: getAlpnProtocolsList, reason: merged with bridge method [inline-methods] */
            public ProtocolStringList mo23176getAlpnProtocolsList() {
                return this.alpnProtocols_.getUnmodifiableView();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
            public int getAlpnProtocolsCount() {
                return this.alpnProtocols_.size();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
            public String getAlpnProtocols(int i) {
                return (String) this.alpnProtocols_.get(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.TlsOptionsOrBuilder
            public ByteString getAlpnProtocolsBytes(int i) {
                return this.alpnProtocols_.getByteString(i);
            }

            public Builder setAlpnProtocols(int i, String str) {
                str.getClass();
                ensureAlpnProtocolsIsMutable();
                this.alpnProtocols_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addAlpnProtocols(String str) {
                str.getClass();
                ensureAlpnProtocolsIsMutable();
                this.alpnProtocols_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllAlpnProtocols(Iterable<String> iterable) {
                ensureAlpnProtocolsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.alpnProtocols_);
                onChanged();
                return this;
            }

            public Builder clearAlpnProtocols() {
                this.alpnProtocols_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addAlpnProtocolsBytes(ByteString byteString) {
                byteString.getClass();
                TlsOptions.checkByteStringIsUtf8(byteString);
                ensureAlpnProtocolsIsMutable();
                this.alpnProtocols_.add(byteString);
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23221setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m23215mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HealthCheckOrBuilder {
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> altPortBuilder_;
        private UInt32Value altPort_;
        private boolean alwaysLogHealthCheckFailures_;
        private SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> customHealthCheckBuilder_;
        private Object eventLogPath_;
        private SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> eventServiceBuilder_;
        private EventServiceConfig eventService_;
        private SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> grpcHealthCheckBuilder_;
        private int healthCheckerCase_;
        private Object healthChecker_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> healthyEdgeIntervalBuilder_;
        private Duration healthyEdgeInterval_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> healthyThresholdBuilder_;
        private UInt32Value healthyThreshold_;
        private SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> httpHealthCheckBuilder_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> initialJitterBuilder_;
        private Duration initialJitter_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> intervalBuilder_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> intervalJitterBuilder_;
        private int intervalJitterPercent_;
        private Duration intervalJitter_;
        private Duration interval_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> noTrafficIntervalBuilder_;
        private Duration noTrafficInterval_;
        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> reuseConnectionBuilder_;
        private BoolValue reuseConnection_;
        private SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> tcpHealthCheckBuilder_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> timeoutBuilder_;
        private Duration timeout_;
        private SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> tlsOptionsBuilder_;
        private TlsOptions tlsOptions_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> transportSocketMatchCriteriaBuilder_;
        private Struct transportSocketMatchCriteria_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> unhealthyEdgeIntervalBuilder_;
        private Duration unhealthyEdgeInterval_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> unhealthyIntervalBuilder_;
        private Duration unhealthyInterval_;
        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> unhealthyThresholdBuilder_;
        private UInt32Value unhealthyThreshold_;

        private Builder() {
            this.healthCheckerCase_ = 0;
            this.eventLogPath_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.healthCheckerCase_ = 0;
            this.eventLogPath_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean getAlwaysLogHealthCheckFailures() {
            return this.alwaysLogHealthCheckFailures_;
        }

        public Builder setAlwaysLogHealthCheckFailures(boolean z) {
            this.alwaysLogHealthCheckFailures_ = z;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public int getIntervalJitterPercent() {
            return this.intervalJitterPercent_;
        }

        public Builder setIntervalJitterPercent(int i) {
            this.intervalJitterPercent_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasAltPort() {
            return (this.altPortBuilder_ == null && this.altPort_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasCustomHealthCheck() {
            return this.healthCheckerCase_ == 13;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasEventService() {
            return (this.eventServiceBuilder_ == null && this.eventService_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasGrpcHealthCheck() {
            return this.healthCheckerCase_ == 11;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasHealthyEdgeInterval() {
            return (this.healthyEdgeIntervalBuilder_ == null && this.healthyEdgeInterval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasHealthyThreshold() {
            return (this.healthyThresholdBuilder_ == null && this.healthyThreshold_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasHttpHealthCheck() {
            return this.healthCheckerCase_ == 8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasInitialJitter() {
            return (this.initialJitterBuilder_ == null && this.initialJitter_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasInterval() {
            return (this.intervalBuilder_ == null && this.interval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasIntervalJitter() {
            return (this.intervalJitterBuilder_ == null && this.intervalJitter_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasNoTrafficInterval() {
            return (this.noTrafficIntervalBuilder_ == null && this.noTrafficInterval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasReuseConnection() {
            return (this.reuseConnectionBuilder_ == null && this.reuseConnection_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasTcpHealthCheck() {
            return this.healthCheckerCase_ == 9;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasTimeout() {
            return (this.timeoutBuilder_ == null && this.timeout_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasTlsOptions() {
            return (this.tlsOptionsBuilder_ == null && this.tlsOptions_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasTransportSocketMatchCriteria() {
            return (this.transportSocketMatchCriteriaBuilder_ == null && this.transportSocketMatchCriteria_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasUnhealthyEdgeInterval() {
            return (this.unhealthyEdgeIntervalBuilder_ == null && this.unhealthyEdgeInterval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasUnhealthyInterval() {
            return (this.unhealthyIntervalBuilder_ == null && this.unhealthyInterval_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public boolean hasUnhealthyThreshold() {
            return (this.unhealthyThresholdBuilder_ == null && this.unhealthyThreshold_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_fieldAccessorTable.ensureFieldAccessorsInitialized(HealthCheck.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = HealthCheck.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22869clear() {
            super.clear();
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            if (this.intervalBuilder_ == null) {
                this.interval_ = null;
            } else {
                this.interval_ = null;
                this.intervalBuilder_ = null;
            }
            if (this.initialJitterBuilder_ == null) {
                this.initialJitter_ = null;
            } else {
                this.initialJitter_ = null;
                this.initialJitterBuilder_ = null;
            }
            if (this.intervalJitterBuilder_ == null) {
                this.intervalJitter_ = null;
            } else {
                this.intervalJitter_ = null;
                this.intervalJitterBuilder_ = null;
            }
            this.intervalJitterPercent_ = 0;
            if (this.unhealthyThresholdBuilder_ == null) {
                this.unhealthyThreshold_ = null;
            } else {
                this.unhealthyThreshold_ = null;
                this.unhealthyThresholdBuilder_ = null;
            }
            if (this.healthyThresholdBuilder_ == null) {
                this.healthyThreshold_ = null;
            } else {
                this.healthyThreshold_ = null;
                this.healthyThresholdBuilder_ = null;
            }
            if (this.altPortBuilder_ == null) {
                this.altPort_ = null;
            } else {
                this.altPort_ = null;
                this.altPortBuilder_ = null;
            }
            if (this.reuseConnectionBuilder_ == null) {
                this.reuseConnection_ = null;
            } else {
                this.reuseConnection_ = null;
                this.reuseConnectionBuilder_ = null;
            }
            if (this.noTrafficIntervalBuilder_ == null) {
                this.noTrafficInterval_ = null;
            } else {
                this.noTrafficInterval_ = null;
                this.noTrafficIntervalBuilder_ = null;
            }
            if (this.unhealthyIntervalBuilder_ == null) {
                this.unhealthyInterval_ = null;
            } else {
                this.unhealthyInterval_ = null;
                this.unhealthyIntervalBuilder_ = null;
            }
            if (this.unhealthyEdgeIntervalBuilder_ == null) {
                this.unhealthyEdgeInterval_ = null;
            } else {
                this.unhealthyEdgeInterval_ = null;
                this.unhealthyEdgeIntervalBuilder_ = null;
            }
            if (this.healthyEdgeIntervalBuilder_ == null) {
                this.healthyEdgeInterval_ = null;
            } else {
                this.healthyEdgeInterval_ = null;
                this.healthyEdgeIntervalBuilder_ = null;
            }
            this.eventLogPath_ = "";
            if (this.eventServiceBuilder_ == null) {
                this.eventService_ = null;
            } else {
                this.eventService_ = null;
                this.eventServiceBuilder_ = null;
            }
            this.alwaysLogHealthCheckFailures_ = false;
            if (this.tlsOptionsBuilder_ == null) {
                this.tlsOptions_ = null;
            } else {
                this.tlsOptions_ = null;
                this.tlsOptionsBuilder_ = null;
            }
            if (this.transportSocketMatchCriteriaBuilder_ == null) {
                this.transportSocketMatchCriteria_ = null;
            } else {
                this.transportSocketMatchCriteria_ = null;
                this.transportSocketMatchCriteriaBuilder_ = null;
            }
            this.healthCheckerCase_ = 0;
            this.healthChecker_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return HealthCheckProto.internal_static_envoy_config_core_v3_HealthCheck_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HealthCheck m22882getDefaultInstanceForType() {
            return HealthCheck.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HealthCheck m22863build() throws UninitializedMessageException {
            HealthCheck healthCheckM22865buildPartial = m22865buildPartial();
            if (healthCheckM22865buildPartial.isInitialized()) {
                return healthCheckM22865buildPartial;
            }
            throw newUninitializedMessageException(healthCheckM22865buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HealthCheck m22865buildPartial() {
            HealthCheck healthCheck = new HealthCheck(this);
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                healthCheck.timeout_ = this.timeout_;
            } else {
                healthCheck.timeout_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV32 = this.intervalBuilder_;
            if (singleFieldBuilderV32 == null) {
                healthCheck.interval_ = this.interval_;
            } else {
                healthCheck.interval_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV33 = this.initialJitterBuilder_;
            if (singleFieldBuilderV33 == null) {
                healthCheck.initialJitter_ = this.initialJitter_;
            } else {
                healthCheck.initialJitter_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV34 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV34 == null) {
                healthCheck.intervalJitter_ = this.intervalJitter_;
            } else {
                healthCheck.intervalJitter_ = singleFieldBuilderV34.build();
            }
            healthCheck.intervalJitterPercent_ = this.intervalJitterPercent_;
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV35 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV35 == null) {
                healthCheck.unhealthyThreshold_ = this.unhealthyThreshold_;
            } else {
                healthCheck.unhealthyThreshold_ = singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV36 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV36 == null) {
                healthCheck.healthyThreshold_ = this.healthyThreshold_;
            } else {
                healthCheck.healthyThreshold_ = singleFieldBuilderV36.build();
            }
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV37 = this.altPortBuilder_;
            if (singleFieldBuilderV37 == null) {
                healthCheck.altPort_ = this.altPort_;
            } else {
                healthCheck.altPort_ = singleFieldBuilderV37.build();
            }
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV38 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV38 == null) {
                healthCheck.reuseConnection_ = this.reuseConnection_;
            } else {
                healthCheck.reuseConnection_ = singleFieldBuilderV38.build();
            }
            if (this.healthCheckerCase_ == 8) {
                SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV39 = this.httpHealthCheckBuilder_;
                if (singleFieldBuilderV39 == null) {
                    healthCheck.healthChecker_ = this.healthChecker_;
                } else {
                    healthCheck.healthChecker_ = singleFieldBuilderV39.build();
                }
            }
            if (this.healthCheckerCase_ == 9) {
                SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV310 = this.tcpHealthCheckBuilder_;
                if (singleFieldBuilderV310 == null) {
                    healthCheck.healthChecker_ = this.healthChecker_;
                } else {
                    healthCheck.healthChecker_ = singleFieldBuilderV310.build();
                }
            }
            if (this.healthCheckerCase_ == 11) {
                SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV311 = this.grpcHealthCheckBuilder_;
                if (singleFieldBuilderV311 == null) {
                    healthCheck.healthChecker_ = this.healthChecker_;
                } else {
                    healthCheck.healthChecker_ = singleFieldBuilderV311.build();
                }
            }
            if (this.healthCheckerCase_ == 13) {
                SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV312 = this.customHealthCheckBuilder_;
                if (singleFieldBuilderV312 == null) {
                    healthCheck.healthChecker_ = this.healthChecker_;
                } else {
                    healthCheck.healthChecker_ = singleFieldBuilderV312.build();
                }
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV313 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV313 == null) {
                healthCheck.noTrafficInterval_ = this.noTrafficInterval_;
            } else {
                healthCheck.noTrafficInterval_ = singleFieldBuilderV313.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV314 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV314 == null) {
                healthCheck.unhealthyInterval_ = this.unhealthyInterval_;
            } else {
                healthCheck.unhealthyInterval_ = singleFieldBuilderV314.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV315 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV315 == null) {
                healthCheck.unhealthyEdgeInterval_ = this.unhealthyEdgeInterval_;
            } else {
                healthCheck.unhealthyEdgeInterval_ = singleFieldBuilderV315.build();
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV316 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV316 == null) {
                healthCheck.healthyEdgeInterval_ = this.healthyEdgeInterval_;
            } else {
                healthCheck.healthyEdgeInterval_ = singleFieldBuilderV316.build();
            }
            healthCheck.eventLogPath_ = this.eventLogPath_;
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV317 = this.eventServiceBuilder_;
            if (singleFieldBuilderV317 == null) {
                healthCheck.eventService_ = this.eventService_;
            } else {
                healthCheck.eventService_ = singleFieldBuilderV317.build();
            }
            healthCheck.alwaysLogHealthCheckFailures_ = this.alwaysLogHealthCheckFailures_;
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV318 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV318 == null) {
                healthCheck.tlsOptions_ = this.tlsOptions_;
            } else {
                healthCheck.tlsOptions_ = singleFieldBuilderV318.build();
            }
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV319 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV319 == null) {
                healthCheck.transportSocketMatchCriteria_ = this.transportSocketMatchCriteria_;
            } else {
                healthCheck.transportSocketMatchCriteria_ = singleFieldBuilderV319.build();
            }
            healthCheck.healthCheckerCase_ = this.healthCheckerCase_;
            onBuilt();
            return healthCheck;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22881clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22893setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22871clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22874clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22895setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22861addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m22886mergeFrom(Message message) {
            if (message instanceof HealthCheck) {
                return mergeFrom((HealthCheck) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HealthCheck healthCheck) {
            if (healthCheck == HealthCheck.getDefaultInstance()) {
                return this;
            }
            if (healthCheck.hasTimeout()) {
                mergeTimeout(healthCheck.getTimeout());
            }
            if (healthCheck.hasInterval()) {
                mergeInterval(healthCheck.getInterval());
            }
            if (healthCheck.hasInitialJitter()) {
                mergeInitialJitter(healthCheck.getInitialJitter());
            }
            if (healthCheck.hasIntervalJitter()) {
                mergeIntervalJitter(healthCheck.getIntervalJitter());
            }
            if (healthCheck.getIntervalJitterPercent() != 0) {
                setIntervalJitterPercent(healthCheck.getIntervalJitterPercent());
            }
            if (healthCheck.hasUnhealthyThreshold()) {
                mergeUnhealthyThreshold(healthCheck.getUnhealthyThreshold());
            }
            if (healthCheck.hasHealthyThreshold()) {
                mergeHealthyThreshold(healthCheck.getHealthyThreshold());
            }
            if (healthCheck.hasAltPort()) {
                mergeAltPort(healthCheck.getAltPort());
            }
            if (healthCheck.hasReuseConnection()) {
                mergeReuseConnection(healthCheck.getReuseConnection());
            }
            if (healthCheck.hasNoTrafficInterval()) {
                mergeNoTrafficInterval(healthCheck.getNoTrafficInterval());
            }
            if (healthCheck.hasUnhealthyInterval()) {
                mergeUnhealthyInterval(healthCheck.getUnhealthyInterval());
            }
            if (healthCheck.hasUnhealthyEdgeInterval()) {
                mergeUnhealthyEdgeInterval(healthCheck.getUnhealthyEdgeInterval());
            }
            if (healthCheck.hasHealthyEdgeInterval()) {
                mergeHealthyEdgeInterval(healthCheck.getHealthyEdgeInterval());
            }
            if (!healthCheck.getEventLogPath().isEmpty()) {
                this.eventLogPath_ = healthCheck.eventLogPath_;
                onChanged();
            }
            if (healthCheck.hasEventService()) {
                mergeEventService(healthCheck.getEventService());
            }
            if (healthCheck.getAlwaysLogHealthCheckFailures()) {
                setAlwaysLogHealthCheckFailures(healthCheck.getAlwaysLogHealthCheckFailures());
            }
            if (healthCheck.hasTlsOptions()) {
                mergeTlsOptions(healthCheck.getTlsOptions());
            }
            if (healthCheck.hasTransportSocketMatchCriteria()) {
                mergeTransportSocketMatchCriteria(healthCheck.getTransportSocketMatchCriteria());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase[healthCheck.getHealthCheckerCase().ordinal()];
            if (i == 1) {
                mergeHttpHealthCheck(healthCheck.getHttpHealthCheck());
            } else if (i == 2) {
                mergeTcpHealthCheck(healthCheck.getTcpHealthCheck());
            } else if (i == 3) {
                mergeGrpcHealthCheck(healthCheck.getGrpcHealthCheck());
            } else if (i == 4) {
                mergeCustomHealthCheck(healthCheck.getCustomHealthCheck());
            }
            m22891mergeUnknownFields(healthCheck.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Builder m22887mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.access$10600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck.Builder.m22887mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public HealthCheckerCase getHealthCheckerCase() {
            return HealthCheckerCase.forNumber(this.healthCheckerCase_);
        }

        public Builder clearHealthChecker() {
            this.healthCheckerCase_ = 0;
            this.healthChecker_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.timeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.timeout_;
                if (duration2 != null) {
                    this.timeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.timeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearTimeout() {
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
                onChanged();
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getTimeoutBuilder() {
            onChanged();
            return getTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getTimeoutFieldBuilder() {
            if (this.timeoutBuilder_ == null) {
                this.timeoutBuilder_ = new SingleFieldBuilderV3<>(getTimeout(), getParentForChildren(), isClean());
                this.timeout_ = null;
            }
            return this.timeoutBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.interval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.interval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.interval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.interval_;
                if (duration2 != null) {
                    this.interval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.interval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearInterval() {
            if (this.intervalBuilder_ == null) {
                this.interval_ = null;
                onChanged();
            } else {
                this.interval_ = null;
                this.intervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getIntervalBuilder() {
            onChanged();
            return getIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.interval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getIntervalFieldBuilder() {
            if (this.intervalBuilder_ == null) {
                this.intervalBuilder_ = new SingleFieldBuilderV3<>(getInterval(), getParentForChildren(), isClean());
                this.interval_ = null;
            }
            return this.intervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getInitialJitter() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialJitterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.initialJitter_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setInitialJitter(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.initialJitter_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setInitialJitter(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.initialJitter_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeInitialJitter(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.initialJitter_;
                if (duration2 != null) {
                    this.initialJitter_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.initialJitter_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearInitialJitter() {
            if (this.initialJitterBuilder_ == null) {
                this.initialJitter_ = null;
                onChanged();
            } else {
                this.initialJitter_ = null;
                this.initialJitterBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getInitialJitterBuilder() {
            onChanged();
            return getInitialJitterFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getInitialJitterOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.initialJitterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.initialJitter_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getInitialJitterFieldBuilder() {
            if (this.initialJitterBuilder_ == null) {
                this.initialJitterBuilder_ = new SingleFieldBuilderV3<>(getInitialJitter(), getParentForChildren(), isClean());
                this.initialJitter_ = null;
            }
            return this.initialJitterBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getIntervalJitter() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.intervalJitter_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setIntervalJitter(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.intervalJitter_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setIntervalJitter(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.intervalJitter_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeIntervalJitter(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.intervalJitter_;
                if (duration2 != null) {
                    this.intervalJitter_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.intervalJitter_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearIntervalJitter() {
            if (this.intervalJitterBuilder_ == null) {
                this.intervalJitter_ = null;
                onChanged();
            } else {
                this.intervalJitter_ = null;
                this.intervalJitterBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getIntervalJitterBuilder() {
            onChanged();
            return getIntervalJitterFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getIntervalJitterOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.intervalJitterBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.intervalJitter_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getIntervalJitterFieldBuilder() {
            if (this.intervalJitterBuilder_ == null) {
                this.intervalJitterBuilder_ = new SingleFieldBuilderV3<>(getIntervalJitter(), getParentForChildren(), isClean());
                this.intervalJitter_ = null;
            }
            return this.intervalJitterBuilder_;
        }

        public Builder clearIntervalJitterPercent() {
            this.intervalJitterPercent_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32Value getUnhealthyThreshold() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.unhealthyThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setUnhealthyThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.unhealthyThreshold_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setUnhealthyThreshold(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.unhealthyThreshold_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUnhealthyThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.unhealthyThreshold_;
                if (uInt32Value2 != null) {
                    this.unhealthyThreshold_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.unhealthyThreshold_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearUnhealthyThreshold() {
            if (this.unhealthyThresholdBuilder_ == null) {
                this.unhealthyThreshold_ = null;
                onChanged();
            } else {
                this.unhealthyThreshold_ = null;
                this.unhealthyThresholdBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getUnhealthyThresholdBuilder() {
            onChanged();
            return getUnhealthyThresholdFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32ValueOrBuilder getUnhealthyThresholdOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.unhealthyThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.unhealthyThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getUnhealthyThresholdFieldBuilder() {
            if (this.unhealthyThresholdBuilder_ == null) {
                this.unhealthyThresholdBuilder_ = new SingleFieldBuilderV3<>(getUnhealthyThreshold(), getParentForChildren(), isClean());
                this.unhealthyThreshold_ = null;
            }
            return this.unhealthyThresholdBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32Value getHealthyThreshold() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.healthyThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setHealthyThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.healthyThreshold_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setHealthyThreshold(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthyThreshold_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeHealthyThreshold(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.healthyThreshold_;
                if (uInt32Value2 != null) {
                    this.healthyThreshold_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.healthyThreshold_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearHealthyThreshold() {
            if (this.healthyThresholdBuilder_ == null) {
                this.healthyThreshold_ = null;
                onChanged();
            } else {
                this.healthyThreshold_ = null;
                this.healthyThresholdBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getHealthyThresholdBuilder() {
            onChanged();
            return getHealthyThresholdFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32ValueOrBuilder getHealthyThresholdOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.healthyThresholdBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.healthyThreshold_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getHealthyThresholdFieldBuilder() {
            if (this.healthyThresholdBuilder_ == null) {
                this.healthyThresholdBuilder_ = new SingleFieldBuilderV3<>(getHealthyThreshold(), getParentForChildren(), isClean());
                this.healthyThreshold_ = null;
            }
            return this.healthyThresholdBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32Value getAltPort() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.altPortBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.altPort_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        public Builder setAltPort(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.altPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                uInt32Value.getClass();
                this.altPort_ = uInt32Value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(uInt32Value);
            }
            return this;
        }

        public Builder setAltPort(UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.altPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.altPort_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAltPort(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.altPortBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.altPort_;
                if (uInt32Value2 != null) {
                    this.altPort_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.altPort_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearAltPort() {
            if (this.altPortBuilder_ == null) {
                this.altPort_ = null;
                onChanged();
            } else {
                this.altPort_ = null;
                this.altPortBuilder_ = null;
            }
            return this;
        }

        public UInt32Value.Builder getAltPortBuilder() {
            onChanged();
            return getAltPortFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public UInt32ValueOrBuilder getAltPortOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.altPortBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.altPort_;
            return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, UInt32Value.Builder, UInt32ValueOrBuilder> getAltPortFieldBuilder() {
            if (this.altPortBuilder_ == null) {
                this.altPortBuilder_ = new SingleFieldBuilderV3<>(getAltPort(), getParentForChildren(), isClean());
                this.altPort_ = null;
            }
            return this.altPortBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public BoolValue getReuseConnection() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            BoolValue boolValue = this.reuseConnection_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        public Builder setReuseConnection(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV3 == null) {
                boolValue.getClass();
                this.reuseConnection_ = boolValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(boolValue);
            }
            return this;
        }

        public Builder setReuseConnection(BoolValue.Builder builder) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.reuseConnection_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeReuseConnection(BoolValue boolValue) {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV3 == null) {
                BoolValue boolValue2 = this.reuseConnection_;
                if (boolValue2 != null) {
                    this.reuseConnection_ = BoolValue.newBuilder(boolValue2).mergeFrom(boolValue).buildPartial();
                } else {
                    this.reuseConnection_ = boolValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(boolValue);
            }
            return this;
        }

        public Builder clearReuseConnection() {
            if (this.reuseConnectionBuilder_ == null) {
                this.reuseConnection_ = null;
                onChanged();
            } else {
                this.reuseConnection_ = null;
                this.reuseConnectionBuilder_ = null;
            }
            return this;
        }

        public BoolValue.Builder getReuseConnectionBuilder() {
            onChanged();
            return getReuseConnectionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public BoolValueOrBuilder getReuseConnectionOrBuilder() {
            SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> singleFieldBuilderV3 = this.reuseConnectionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            BoolValue boolValue = this.reuseConnection_;
            return boolValue == null ? BoolValue.getDefaultInstance() : boolValue;
        }

        private SingleFieldBuilderV3<BoolValue, BoolValue.Builder, BoolValueOrBuilder> getReuseConnectionFieldBuilder() {
            if (this.reuseConnectionBuilder_ == null) {
                this.reuseConnectionBuilder_ = new SingleFieldBuilderV3<>(getReuseConnection(), getParentForChildren(), isClean());
                this.reuseConnection_ = null;
            }
            return this.reuseConnectionBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public HttpHealthCheck getHttpHealthCheck() {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3 = this.httpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ == 8) {
                    return (HttpHealthCheck) this.healthChecker_;
                }
                return HttpHealthCheck.getDefaultInstance();
            }
            if (this.healthCheckerCase_ == 8) {
                return singleFieldBuilderV3.getMessage();
            }
            return HttpHealthCheck.getDefaultInstance();
        }

        public Builder setHttpHealthCheck(HttpHealthCheck httpHealthCheck) {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3 = this.httpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                httpHealthCheck.getClass();
                this.healthChecker_ = httpHealthCheck;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(httpHealthCheck);
            }
            this.healthCheckerCase_ = 8;
            return this;
        }

        public Builder setHttpHealthCheck(HttpHealthCheck.Builder builder) {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3 = this.httpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthChecker_ = builder.m23002build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23002build());
            }
            this.healthCheckerCase_ = 8;
            return this;
        }

        public Builder mergeHttpHealthCheck(HttpHealthCheck httpHealthCheck) {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3 = this.httpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ != 8 || this.healthChecker_ == HttpHealthCheck.getDefaultInstance()) {
                    this.healthChecker_ = httpHealthCheck;
                } else {
                    this.healthChecker_ = HttpHealthCheck.newBuilder((HttpHealthCheck) this.healthChecker_).mergeFrom(httpHealthCheck).m23004buildPartial();
                }
                onChanged();
            } else {
                if (this.healthCheckerCase_ == 8) {
                    singleFieldBuilderV3.mergeFrom(httpHealthCheck);
                }
                this.httpHealthCheckBuilder_.setMessage(httpHealthCheck);
            }
            this.healthCheckerCase_ = 8;
            return this;
        }

        public Builder clearHttpHealthCheck() {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3 = this.httpHealthCheckBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.healthCheckerCase_ == 8) {
                    this.healthCheckerCase_ = 0;
                    this.healthChecker_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.healthCheckerCase_ == 8) {
                this.healthCheckerCase_ = 0;
                this.healthChecker_ = null;
                onChanged();
            }
            return this;
        }

        public HttpHealthCheck.Builder getHttpHealthCheckBuilder() {
            return getHttpHealthCheckFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public HttpHealthCheckOrBuilder getHttpHealthCheckOrBuilder() {
            SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> singleFieldBuilderV3;
            int i = this.healthCheckerCase_;
            if (i == 8 && (singleFieldBuilderV3 = this.httpHealthCheckBuilder_) != null) {
                return (HttpHealthCheckOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 8) {
                return (HttpHealthCheck) this.healthChecker_;
            }
            return HttpHealthCheck.getDefaultInstance();
        }

        private SingleFieldBuilderV3<HttpHealthCheck, HttpHealthCheck.Builder, HttpHealthCheckOrBuilder> getHttpHealthCheckFieldBuilder() {
            if (this.httpHealthCheckBuilder_ == null) {
                if (this.healthCheckerCase_ != 8) {
                    this.healthChecker_ = HttpHealthCheck.getDefaultInstance();
                }
                this.httpHealthCheckBuilder_ = new SingleFieldBuilderV3<>((HttpHealthCheck) this.healthChecker_, getParentForChildren(), isClean());
                this.healthChecker_ = null;
            }
            this.healthCheckerCase_ = 8;
            onChanged();
            return this.httpHealthCheckBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public TcpHealthCheck getTcpHealthCheck() {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3 = this.tcpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ == 9) {
                    return (TcpHealthCheck) this.healthChecker_;
                }
                return TcpHealthCheck.getDefaultInstance();
            }
            if (this.healthCheckerCase_ == 9) {
                return singleFieldBuilderV3.getMessage();
            }
            return TcpHealthCheck.getDefaultInstance();
        }

        public Builder setTcpHealthCheck(TcpHealthCheck tcpHealthCheck) {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3 = this.tcpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                tcpHealthCheck.getClass();
                this.healthChecker_ = tcpHealthCheck;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tcpHealthCheck);
            }
            this.healthCheckerCase_ = 9;
            return this;
        }

        public Builder setTcpHealthCheck(TcpHealthCheck.Builder builder) {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3 = this.tcpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthChecker_ = builder.m23140build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23140build());
            }
            this.healthCheckerCase_ = 9;
            return this;
        }

        public Builder mergeTcpHealthCheck(TcpHealthCheck tcpHealthCheck) {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3 = this.tcpHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ != 9 || this.healthChecker_ == TcpHealthCheck.getDefaultInstance()) {
                    this.healthChecker_ = tcpHealthCheck;
                } else {
                    this.healthChecker_ = TcpHealthCheck.newBuilder((TcpHealthCheck) this.healthChecker_).mergeFrom(tcpHealthCheck).m23142buildPartial();
                }
                onChanged();
            } else {
                if (this.healthCheckerCase_ == 9) {
                    singleFieldBuilderV3.mergeFrom(tcpHealthCheck);
                }
                this.tcpHealthCheckBuilder_.setMessage(tcpHealthCheck);
            }
            this.healthCheckerCase_ = 9;
            return this;
        }

        public Builder clearTcpHealthCheck() {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3 = this.tcpHealthCheckBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.healthCheckerCase_ == 9) {
                    this.healthCheckerCase_ = 0;
                    this.healthChecker_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.healthCheckerCase_ == 9) {
                this.healthCheckerCase_ = 0;
                this.healthChecker_ = null;
                onChanged();
            }
            return this;
        }

        public TcpHealthCheck.Builder getTcpHealthCheckBuilder() {
            return getTcpHealthCheckFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public TcpHealthCheckOrBuilder getTcpHealthCheckOrBuilder() {
            SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> singleFieldBuilderV3;
            int i = this.healthCheckerCase_;
            if (i == 9 && (singleFieldBuilderV3 = this.tcpHealthCheckBuilder_) != null) {
                return (TcpHealthCheckOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 9) {
                return (TcpHealthCheck) this.healthChecker_;
            }
            return TcpHealthCheck.getDefaultInstance();
        }

        private SingleFieldBuilderV3<TcpHealthCheck, TcpHealthCheck.Builder, TcpHealthCheckOrBuilder> getTcpHealthCheckFieldBuilder() {
            if (this.tcpHealthCheckBuilder_ == null) {
                if (this.healthCheckerCase_ != 9) {
                    this.healthChecker_ = TcpHealthCheck.getDefaultInstance();
                }
                this.tcpHealthCheckBuilder_ = new SingleFieldBuilderV3<>((TcpHealthCheck) this.healthChecker_, getParentForChildren(), isClean());
                this.healthChecker_ = null;
            }
            this.healthCheckerCase_ = 9;
            onChanged();
            return this.tcpHealthCheckBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public GrpcHealthCheck getGrpcHealthCheck() {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3 = this.grpcHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ == 11) {
                    return (GrpcHealthCheck) this.healthChecker_;
                }
                return GrpcHealthCheck.getDefaultInstance();
            }
            if (this.healthCheckerCase_ == 11) {
                return singleFieldBuilderV3.getMessage();
            }
            return GrpcHealthCheck.getDefaultInstance();
        }

        public Builder setGrpcHealthCheck(GrpcHealthCheck grpcHealthCheck) {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3 = this.grpcHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                grpcHealthCheck.getClass();
                this.healthChecker_ = grpcHealthCheck;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(grpcHealthCheck);
            }
            this.healthCheckerCase_ = 11;
            return this;
        }

        public Builder setGrpcHealthCheck(GrpcHealthCheck.Builder builder) {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3 = this.grpcHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthChecker_ = builder.m22955build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m22955build());
            }
            this.healthCheckerCase_ = 11;
            return this;
        }

        public Builder mergeGrpcHealthCheck(GrpcHealthCheck grpcHealthCheck) {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3 = this.grpcHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ != 11 || this.healthChecker_ == GrpcHealthCheck.getDefaultInstance()) {
                    this.healthChecker_ = grpcHealthCheck;
                } else {
                    this.healthChecker_ = GrpcHealthCheck.newBuilder((GrpcHealthCheck) this.healthChecker_).mergeFrom(grpcHealthCheck).m22957buildPartial();
                }
                onChanged();
            } else {
                if (this.healthCheckerCase_ == 11) {
                    singleFieldBuilderV3.mergeFrom(grpcHealthCheck);
                }
                this.grpcHealthCheckBuilder_.setMessage(grpcHealthCheck);
            }
            this.healthCheckerCase_ = 11;
            return this;
        }

        public Builder clearGrpcHealthCheck() {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3 = this.grpcHealthCheckBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.healthCheckerCase_ == 11) {
                    this.healthCheckerCase_ = 0;
                    this.healthChecker_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.healthCheckerCase_ == 11) {
                this.healthCheckerCase_ = 0;
                this.healthChecker_ = null;
                onChanged();
            }
            return this;
        }

        public GrpcHealthCheck.Builder getGrpcHealthCheckBuilder() {
            return getGrpcHealthCheckFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public GrpcHealthCheckOrBuilder getGrpcHealthCheckOrBuilder() {
            SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> singleFieldBuilderV3;
            int i = this.healthCheckerCase_;
            if (i == 11 && (singleFieldBuilderV3 = this.grpcHealthCheckBuilder_) != null) {
                return (GrpcHealthCheckOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 11) {
                return (GrpcHealthCheck) this.healthChecker_;
            }
            return GrpcHealthCheck.getDefaultInstance();
        }

        private SingleFieldBuilderV3<GrpcHealthCheck, GrpcHealthCheck.Builder, GrpcHealthCheckOrBuilder> getGrpcHealthCheckFieldBuilder() {
            if (this.grpcHealthCheckBuilder_ == null) {
                if (this.healthCheckerCase_ != 11) {
                    this.healthChecker_ = GrpcHealthCheck.getDefaultInstance();
                }
                this.grpcHealthCheckBuilder_ = new SingleFieldBuilderV3<>((GrpcHealthCheck) this.healthChecker_, getParentForChildren(), isClean());
                this.healthChecker_ = null;
            }
            this.healthCheckerCase_ = 11;
            onChanged();
            return this.grpcHealthCheckBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public CustomHealthCheck getCustomHealthCheck() {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3 = this.customHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ == 13) {
                    return (CustomHealthCheck) this.healthChecker_;
                }
                return CustomHealthCheck.getDefaultInstance();
            }
            if (this.healthCheckerCase_ == 13) {
                return singleFieldBuilderV3.getMessage();
            }
            return CustomHealthCheck.getDefaultInstance();
        }

        public Builder setCustomHealthCheck(CustomHealthCheck customHealthCheck) {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3 = this.customHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                customHealthCheck.getClass();
                this.healthChecker_ = customHealthCheck;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(customHealthCheck);
            }
            this.healthCheckerCase_ = 13;
            return this;
        }

        public Builder setCustomHealthCheck(CustomHealthCheck.Builder builder) {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3 = this.customHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthChecker_ = builder.m22909build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m22909build());
            }
            this.healthCheckerCase_ = 13;
            return this;
        }

        public Builder mergeCustomHealthCheck(CustomHealthCheck customHealthCheck) {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3 = this.customHealthCheckBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.healthCheckerCase_ != 13 || this.healthChecker_ == CustomHealthCheck.getDefaultInstance()) {
                    this.healthChecker_ = customHealthCheck;
                } else {
                    this.healthChecker_ = CustomHealthCheck.newBuilder((CustomHealthCheck) this.healthChecker_).mergeFrom(customHealthCheck).m22911buildPartial();
                }
                onChanged();
            } else {
                if (this.healthCheckerCase_ == 13) {
                    singleFieldBuilderV3.mergeFrom(customHealthCheck);
                }
                this.customHealthCheckBuilder_.setMessage(customHealthCheck);
            }
            this.healthCheckerCase_ = 13;
            return this;
        }

        public Builder clearCustomHealthCheck() {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3 = this.customHealthCheckBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.healthCheckerCase_ == 13) {
                    this.healthCheckerCase_ = 0;
                    this.healthChecker_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.healthCheckerCase_ == 13) {
                this.healthCheckerCase_ = 0;
                this.healthChecker_ = null;
                onChanged();
            }
            return this;
        }

        public CustomHealthCheck.Builder getCustomHealthCheckBuilder() {
            return getCustomHealthCheckFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public CustomHealthCheckOrBuilder getCustomHealthCheckOrBuilder() {
            SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> singleFieldBuilderV3;
            int i = this.healthCheckerCase_;
            if (i == 13 && (singleFieldBuilderV3 = this.customHealthCheckBuilder_) != null) {
                return (CustomHealthCheckOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 13) {
                return (CustomHealthCheck) this.healthChecker_;
            }
            return CustomHealthCheck.getDefaultInstance();
        }

        private SingleFieldBuilderV3<CustomHealthCheck, CustomHealthCheck.Builder, CustomHealthCheckOrBuilder> getCustomHealthCheckFieldBuilder() {
            if (this.customHealthCheckBuilder_ == null) {
                if (this.healthCheckerCase_ != 13) {
                    this.healthChecker_ = CustomHealthCheck.getDefaultInstance();
                }
                this.customHealthCheckBuilder_ = new SingleFieldBuilderV3<>((CustomHealthCheck) this.healthChecker_, getParentForChildren(), isClean());
                this.healthChecker_ = null;
            }
            this.healthCheckerCase_ = 13;
            onChanged();
            return this.customHealthCheckBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getNoTrafficInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.noTrafficInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setNoTrafficInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.noTrafficInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setNoTrafficInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.noTrafficInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeNoTrafficInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.noTrafficInterval_;
                if (duration2 != null) {
                    this.noTrafficInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.noTrafficInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearNoTrafficInterval() {
            if (this.noTrafficIntervalBuilder_ == null) {
                this.noTrafficInterval_ = null;
                onChanged();
            } else {
                this.noTrafficInterval_ = null;
                this.noTrafficIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getNoTrafficIntervalBuilder() {
            onChanged();
            return getNoTrafficIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getNoTrafficIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.noTrafficIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.noTrafficInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getNoTrafficIntervalFieldBuilder() {
            if (this.noTrafficIntervalBuilder_ == null) {
                this.noTrafficIntervalBuilder_ = new SingleFieldBuilderV3<>(getNoTrafficInterval(), getParentForChildren(), isClean());
                this.noTrafficInterval_ = null;
            }
            return this.noTrafficIntervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getUnhealthyInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.unhealthyInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setUnhealthyInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.unhealthyInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setUnhealthyInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.unhealthyInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUnhealthyInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.unhealthyInterval_;
                if (duration2 != null) {
                    this.unhealthyInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.unhealthyInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearUnhealthyInterval() {
            if (this.unhealthyIntervalBuilder_ == null) {
                this.unhealthyInterval_ = null;
                onChanged();
            } else {
                this.unhealthyInterval_ = null;
                this.unhealthyIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getUnhealthyIntervalBuilder() {
            onChanged();
            return getUnhealthyIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getUnhealthyIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.unhealthyInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getUnhealthyIntervalFieldBuilder() {
            if (this.unhealthyIntervalBuilder_ == null) {
                this.unhealthyIntervalBuilder_ = new SingleFieldBuilderV3<>(getUnhealthyInterval(), getParentForChildren(), isClean());
                this.unhealthyInterval_ = null;
            }
            return this.unhealthyIntervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getUnhealthyEdgeInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.unhealthyEdgeInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setUnhealthyEdgeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.unhealthyEdgeInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setUnhealthyEdgeInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.unhealthyEdgeInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUnhealthyEdgeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.unhealthyEdgeInterval_;
                if (duration2 != null) {
                    this.unhealthyEdgeInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.unhealthyEdgeInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearUnhealthyEdgeInterval() {
            if (this.unhealthyEdgeIntervalBuilder_ == null) {
                this.unhealthyEdgeInterval_ = null;
                onChanged();
            } else {
                this.unhealthyEdgeInterval_ = null;
                this.unhealthyEdgeIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getUnhealthyEdgeIntervalBuilder() {
            onChanged();
            return getUnhealthyEdgeIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getUnhealthyEdgeIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.unhealthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.unhealthyEdgeInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getUnhealthyEdgeIntervalFieldBuilder() {
            if (this.unhealthyEdgeIntervalBuilder_ == null) {
                this.unhealthyEdgeIntervalBuilder_ = new SingleFieldBuilderV3<>(getUnhealthyEdgeInterval(), getParentForChildren(), isClean());
                this.unhealthyEdgeInterval_ = null;
            }
            return this.unhealthyEdgeIntervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Duration getHealthyEdgeInterval() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.healthyEdgeInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setHealthyEdgeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.healthyEdgeInterval_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setHealthyEdgeInterval(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.healthyEdgeInterval_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeHealthyEdgeInterval(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.healthyEdgeInterval_;
                if (duration2 != null) {
                    this.healthyEdgeInterval_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.healthyEdgeInterval_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearHealthyEdgeInterval() {
            if (this.healthyEdgeIntervalBuilder_ == null) {
                this.healthyEdgeInterval_ = null;
                onChanged();
            } else {
                this.healthyEdgeInterval_ = null;
                this.healthyEdgeIntervalBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getHealthyEdgeIntervalBuilder() {
            onChanged();
            return getHealthyEdgeIntervalFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public DurationOrBuilder getHealthyEdgeIntervalOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.healthyEdgeIntervalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.healthyEdgeInterval_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getHealthyEdgeIntervalFieldBuilder() {
            if (this.healthyEdgeIntervalBuilder_ == null) {
                this.healthyEdgeIntervalBuilder_ = new SingleFieldBuilderV3<>(getHealthyEdgeInterval(), getParentForChildren(), isClean());
                this.healthyEdgeInterval_ = null;
            }
            return this.healthyEdgeIntervalBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public String getEventLogPath() {
            Object obj = this.eventLogPath_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.eventLogPath_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setEventLogPath(String str) {
            str.getClass();
            this.eventLogPath_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public ByteString getEventLogPathBytes() {
            Object obj = this.eventLogPath_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eventLogPath_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setEventLogPathBytes(ByteString byteString) {
            byteString.getClass();
            HealthCheck.checkByteStringIsUtf8(byteString);
            this.eventLogPath_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearEventLogPath() {
            this.eventLogPath_ = HealthCheck.getDefaultInstance().getEventLogPath();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public EventServiceConfig getEventService() {
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV3 = this.eventServiceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            EventServiceConfig eventServiceConfig = this.eventService_;
            return eventServiceConfig == null ? EventServiceConfig.getDefaultInstance() : eventServiceConfig;
        }

        public Builder setEventService(EventServiceConfig eventServiceConfig) {
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV3 = this.eventServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                eventServiceConfig.getClass();
                this.eventService_ = eventServiceConfig;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(eventServiceConfig);
            }
            return this;
        }

        public Builder setEventService(EventServiceConfig.Builder builder) {
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV3 = this.eventServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.eventService_ = builder.m21942build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m21942build());
            }
            return this;
        }

        public Builder mergeEventService(EventServiceConfig eventServiceConfig) {
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV3 = this.eventServiceBuilder_;
            if (singleFieldBuilderV3 == null) {
                EventServiceConfig eventServiceConfig2 = this.eventService_;
                if (eventServiceConfig2 != null) {
                    this.eventService_ = EventServiceConfig.newBuilder(eventServiceConfig2).mergeFrom(eventServiceConfig).m21944buildPartial();
                } else {
                    this.eventService_ = eventServiceConfig;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(eventServiceConfig);
            }
            return this;
        }

        public Builder clearEventService() {
            if (this.eventServiceBuilder_ == null) {
                this.eventService_ = null;
                onChanged();
            } else {
                this.eventService_ = null;
                this.eventServiceBuilder_ = null;
            }
            return this;
        }

        public EventServiceConfig.Builder getEventServiceBuilder() {
            onChanged();
            return getEventServiceFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public EventServiceConfigOrBuilder getEventServiceOrBuilder() {
            SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> singleFieldBuilderV3 = this.eventServiceBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (EventServiceConfigOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            EventServiceConfig eventServiceConfig = this.eventService_;
            return eventServiceConfig == null ? EventServiceConfig.getDefaultInstance() : eventServiceConfig;
        }

        private SingleFieldBuilderV3<EventServiceConfig, EventServiceConfig.Builder, EventServiceConfigOrBuilder> getEventServiceFieldBuilder() {
            if (this.eventServiceBuilder_ == null) {
                this.eventServiceBuilder_ = new SingleFieldBuilderV3<>(getEventService(), getParentForChildren(), isClean());
                this.eventService_ = null;
            }
            return this.eventServiceBuilder_;
        }

        public Builder clearAlwaysLogHealthCheckFailures() {
            this.alwaysLogHealthCheckFailures_ = false;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public TlsOptions getTlsOptions() {
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV3 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            TlsOptions tlsOptions = this.tlsOptions_;
            return tlsOptions == null ? TlsOptions.getDefaultInstance() : tlsOptions;
        }

        public Builder setTlsOptions(TlsOptions tlsOptions) {
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV3 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsOptions.getClass();
                this.tlsOptions_ = tlsOptions;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(tlsOptions);
            }
            return this;
        }

        public Builder setTlsOptions(TlsOptions.Builder builder) {
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV3 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.tlsOptions_ = builder.m23187build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m23187build());
            }
            return this;
        }

        public Builder mergeTlsOptions(TlsOptions tlsOptions) {
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV3 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV3 == null) {
                TlsOptions tlsOptions2 = this.tlsOptions_;
                if (tlsOptions2 != null) {
                    this.tlsOptions_ = TlsOptions.newBuilder(tlsOptions2).mergeFrom(tlsOptions).m23189buildPartial();
                } else {
                    this.tlsOptions_ = tlsOptions;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(tlsOptions);
            }
            return this;
        }

        public Builder clearTlsOptions() {
            if (this.tlsOptionsBuilder_ == null) {
                this.tlsOptions_ = null;
                onChanged();
            } else {
                this.tlsOptions_ = null;
                this.tlsOptionsBuilder_ = null;
            }
            return this;
        }

        public TlsOptions.Builder getTlsOptionsBuilder() {
            onChanged();
            return getTlsOptionsFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public TlsOptionsOrBuilder getTlsOptionsOrBuilder() {
            SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> singleFieldBuilderV3 = this.tlsOptionsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (TlsOptionsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            TlsOptions tlsOptions = this.tlsOptions_;
            return tlsOptions == null ? TlsOptions.getDefaultInstance() : tlsOptions;
        }

        private SingleFieldBuilderV3<TlsOptions, TlsOptions.Builder, TlsOptionsOrBuilder> getTlsOptionsFieldBuilder() {
            if (this.tlsOptionsBuilder_ == null) {
                this.tlsOptionsBuilder_ = new SingleFieldBuilderV3<>(getTlsOptions(), getParentForChildren(), isClean());
                this.tlsOptions_ = null;
            }
            return this.tlsOptionsBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public Struct getTransportSocketMatchCriteria() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.transportSocketMatchCriteria_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        public Builder setTransportSocketMatchCriteria(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV3 == null) {
                struct.getClass();
                this.transportSocketMatchCriteria_ = struct;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(struct);
            }
            return this;
        }

        public Builder setTransportSocketMatchCriteria(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.transportSocketMatchCriteria_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTransportSocketMatchCriteria(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.transportSocketMatchCriteria_;
                if (struct2 != null) {
                    this.transportSocketMatchCriteria_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.transportSocketMatchCriteria_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearTransportSocketMatchCriteria() {
            if (this.transportSocketMatchCriteriaBuilder_ == null) {
                this.transportSocketMatchCriteria_ = null;
                onChanged();
            } else {
                this.transportSocketMatchCriteria_ = null;
                this.transportSocketMatchCriteriaBuilder_ = null;
            }
            return this;
        }

        public Struct.Builder getTransportSocketMatchCriteriaBuilder() {
            onChanged();
            return getTransportSocketMatchCriteriaFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheckOrBuilder
        public StructOrBuilder getTransportSocketMatchCriteriaOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.transportSocketMatchCriteriaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.transportSocketMatchCriteria_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getTransportSocketMatchCriteriaFieldBuilder() {
            if (this.transportSocketMatchCriteriaBuilder_ == null) {
                this.transportSocketMatchCriteriaBuilder_ = new SingleFieldBuilderV3<>(getTransportSocketMatchCriteria(), getParentForChildren(), isClean());
                this.transportSocketMatchCriteria_ = null;
            }
            return this.transportSocketMatchCriteriaBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m22897setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m22891mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthCheck$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$CustomHealthCheck$ConfigTypeCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$Payload$PayloadCase;

        static {
            int[] iArr = new int[HealthCheckerCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase = iArr;
            try {
                iArr[HealthCheckerCase.HTTP_HEALTH_CHECK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase[HealthCheckerCase.TCP_HEALTH_CHECK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase[HealthCheckerCase.GRPC_HEALTH_CHECK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase[HealthCheckerCase.CUSTOM_HEALTH_CHECK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$HealthCheckerCase[HealthCheckerCase.HEALTHCHECKER_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[CustomHealthCheck.ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$CustomHealthCheck$ConfigTypeCase = iArr2;
            try {
                iArr2[CustomHealthCheck.ConfigTypeCase.TYPED_CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$CustomHealthCheck$ConfigTypeCase[CustomHealthCheck.ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[Payload.PayloadCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$Payload$PayloadCase = iArr3;
            try {
                iArr3[Payload.PayloadCase.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$Payload$PayloadCase[Payload.PayloadCase.BINARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$core$v3$HealthCheck$Payload$PayloadCase[Payload.PayloadCase.PAYLOAD_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
