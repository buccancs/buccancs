package io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;

import java.util.List;

/* loaded from: classes6.dex */
public interface NodeOrBuilder extends MessageOrBuilder {
    String getClientFeatures(int i);

    ByteString getClientFeaturesBytes(int i);

    int getClientFeaturesCount();

    /* renamed from: getClientFeaturesList */
    List<String> mo23639getClientFeaturesList();

    String getCluster();

    ByteString getClusterBytes();

    Extension getExtensions(int i);

    int getExtensionsCount();

    List<Extension> getExtensionsList();

    ExtensionOrBuilder getExtensionsOrBuilder(int i);

    List<? extends ExtensionOrBuilder> getExtensionsOrBuilderList();

    String getId();

    ByteString getIdBytes();

    Address getListeningAddresses(int i);

    int getListeningAddressesCount();

    List<Address> getListeningAddressesList();

    AddressOrBuilder getListeningAddressesOrBuilder(int i);

    List<? extends AddressOrBuilder> getListeningAddressesOrBuilderList();

    Locality getLocality();

    LocalityOrBuilder getLocalityOrBuilder();

    Struct getMetadata();

    StructOrBuilder getMetadataOrBuilder();

    BuildVersion getUserAgentBuildVersion();

    BuildVersionOrBuilder getUserAgentBuildVersionOrBuilder();

    String getUserAgentName();

    ByteString getUserAgentNameBytes();

    String getUserAgentVersion();

    ByteString getUserAgentVersionBytes();

    Node.UserAgentVersionTypeCase getUserAgentVersionTypeCase();

    boolean hasLocality();

    boolean hasMetadata();

    boolean hasUserAgentBuildVersion();
}
