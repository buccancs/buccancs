package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRange;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.CidrRangeOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.listener.FilterChainMatch;

import java.util.List;

/* loaded from: classes5.dex */
public interface FilterChainMatchOrBuilder extends MessageOrBuilder {
    String getAddressSuffix();

    ByteString getAddressSuffixBytes();

    String getApplicationProtocols(int i);

    ByteString getApplicationProtocolsBytes(int i);

    int getApplicationProtocolsCount();

    /* renamed from: getApplicationProtocolsList */
    List<String> mo17623getApplicationProtocolsList();

    UInt32Value getDestinationPort();

    UInt32ValueOrBuilder getDestinationPortOrBuilder();

    CidrRange getPrefixRanges(int i);

    int getPrefixRangesCount();

    List<CidrRange> getPrefixRangesList();

    CidrRangeOrBuilder getPrefixRangesOrBuilder(int i);

    List<? extends CidrRangeOrBuilder> getPrefixRangesOrBuilderList();

    String getServerNames(int i);

    ByteString getServerNamesBytes(int i);

    int getServerNamesCount();

    /* renamed from: getServerNamesList */
    List<String> mo17626getServerNamesList();

    int getSourcePorts(int i);

    int getSourcePortsCount();

    List<Integer> getSourcePortsList();

    CidrRange getSourcePrefixRanges(int i);

    int getSourcePrefixRangesCount();

    List<CidrRange> getSourcePrefixRangesList();

    CidrRangeOrBuilder getSourcePrefixRangesOrBuilder(int i);

    List<? extends CidrRangeOrBuilder> getSourcePrefixRangesOrBuilderList();

    FilterChainMatch.ConnectionSourceType getSourceType();

    int getSourceTypeValue();

    UInt32Value getSuffixLen();

    UInt32ValueOrBuilder getSuffixLenOrBuilder();

    String getTransportProtocol();

    ByteString getTransportProtocolBytes();

    boolean hasDestinationPort();

    boolean hasSuffixLen();
}
