package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsParameters;

import java.util.List;

/* loaded from: classes3.dex */
public interface TlsParametersOrBuilder extends MessageOrBuilder {
    String getCipherSuites(int i);

    ByteString getCipherSuitesBytes(int i);

    int getCipherSuitesCount();

    /* renamed from: getCipherSuitesList */
    List<String> mo13881getCipherSuitesList();

    String getEcdhCurves(int i);

    ByteString getEcdhCurvesBytes(int i);

    int getEcdhCurvesCount();

    /* renamed from: getEcdhCurvesList */
    List<String> mo13884getEcdhCurvesList();

    TlsParameters.TlsProtocol getTlsMaximumProtocolVersion();

    int getTlsMaximumProtocolVersionValue();

    TlsParameters.TlsProtocol getTlsMinimumProtocolVersion();

    int getTlsMinimumProtocolVersionValue();
}
