package io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.extensions.transport_sockets.tls.v3.TlsParameters;

import java.util.List;

/* loaded from: classes4.dex */
public interface TlsParametersOrBuilder extends MessageOrBuilder {
    String getCipherSuites(int i);

    ByteString getCipherSuitesBytes(int i);

    int getCipherSuitesCount();

    /* renamed from: getCipherSuitesList */
    List<String> mo32107getCipherSuitesList();

    String getEcdhCurves(int i);

    ByteString getEcdhCurvesBytes(int i);

    int getEcdhCurvesCount();

    /* renamed from: getEcdhCurvesList */
    List<String> mo32110getEcdhCurvesList();

    TlsParameters.TlsProtocol getTlsMaximumProtocolVersion();

    int getTlsMaximumProtocolVersionValue();

    TlsParameters.TlsProtocol getTlsMinimumProtocolVersion();

    int getTlsMinimumProtocolVersionValue();
}
