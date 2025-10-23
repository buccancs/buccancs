package io.grpc.channelz.v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.channelz.v1.Address;

/* loaded from: classes2.dex */
public interface AddressOrBuilder extends MessageOrBuilder {
    Address.AddressCase getAddressCase();

    Address.OtherAddress getOtherAddress();

    Address.OtherAddressOrBuilder getOtherAddressOrBuilder();

    Address.TcpIpAddress getTcpipAddress();

    Address.TcpIpAddressOrBuilder getTcpipAddressOrBuilder();

    Address.UdsAddress getUdsAddress();

    Address.UdsAddressOrBuilder getUdsAddressOrBuilder();

    boolean hasOtherAddress();

    boolean hasTcpipAddress();

    boolean hasUdsAddress();
}
