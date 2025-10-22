package io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.trace.v2.OpenCensusConfig;
import io.opencensus.proto.trace.v1.TraceConfig;
import io.opencensus.proto.trace.v1.TraceConfigOrBuilder;

import java.util.List;

/* loaded from: classes4.dex */
public interface OpenCensusConfigOrBuilder extends MessageOrBuilder {
    OpenCensusConfig.TraceContext getIncomingTraceContext(int i);

    int getIncomingTraceContextCount();

    List<OpenCensusConfig.TraceContext> getIncomingTraceContextList();

    int getIncomingTraceContextValue(int i);

    List<Integer> getIncomingTraceContextValueList();

    String getOcagentAddress();

    ByteString getOcagentAddressBytes();

    boolean getOcagentExporterEnabled();

    GrpcService getOcagentGrpcService();

    GrpcServiceOrBuilder getOcagentGrpcServiceOrBuilder();

    OpenCensusConfig.TraceContext getOutgoingTraceContext(int i);

    int getOutgoingTraceContextCount();

    List<OpenCensusConfig.TraceContext> getOutgoingTraceContextList();

    int getOutgoingTraceContextValue(int i);

    List<Integer> getOutgoingTraceContextValueList();

    String getStackdriverAddress();

    ByteString getStackdriverAddressBytes();

    boolean getStackdriverExporterEnabled();

    GrpcService getStackdriverGrpcService();

    GrpcServiceOrBuilder getStackdriverGrpcServiceOrBuilder();

    String getStackdriverProjectId();

    ByteString getStackdriverProjectIdBytes();

    boolean getStdoutExporterEnabled();

    TraceConfig getTraceConfig();

    TraceConfigOrBuilder getTraceConfigOrBuilder();

    boolean getZipkinExporterEnabled();

    String getZipkinUrl();

    ByteString getZipkinUrlBytes();

    boolean hasOcagentGrpcService();

    boolean hasStackdriverGrpcService();

    boolean hasTraceConfig();
}
