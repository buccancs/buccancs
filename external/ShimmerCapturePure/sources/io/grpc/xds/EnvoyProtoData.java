package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.protobuf.util.Durations;
import com.google.re2j.Pattern;
import com.google.re2j.PatternSyntaxException;
import io.grpc.EquivalentAddressGroup;
import io.grpc.netty.shaded.io.netty.handler.codec.rtsp.RtspHeaders;
import io.grpc.xds.RouteMatch;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ClusterLoadAssignment;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStats;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.UpstreamLocalityStats;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.HealthStatus;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.SocketAddress;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterStats;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.UpstreamLocalityStats;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.HeaderMatcher;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Route;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteAction;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteMatch;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.WeightedCluster;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

import kotlin.time.DurationKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class EnvoyProtoData {
    private EnvoyProtoData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Value convertToValue(Object obj) {
        Value.Builder builderNewBuilder = Value.newBuilder();
        if (obj == null) {
            builderNewBuilder.setNullValue(NullValue.NULL_VALUE);
        } else if (obj instanceof Double) {
            builderNewBuilder.setNumberValue(((Double) obj).doubleValue());
        } else if (obj instanceof String) {
            builderNewBuilder.setStringValue((String) obj);
        } else if (obj instanceof Boolean) {
            builderNewBuilder.setBoolValue(((Boolean) obj).booleanValue());
        } else if (obj instanceof Map) {
            Struct.Builder builderNewBuilder2 = Struct.newBuilder();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                builderNewBuilder2.putFields((String) entry.getKey(), convertToValue(entry.getValue()));
            }
            builderNewBuilder.setStructValue(builderNewBuilder2);
        } else if (obj instanceof List) {
            ListValue.Builder builderNewBuilder3 = ListValue.newBuilder();
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                builderNewBuilder3.addValues(convertToValue(it2.next()));
            }
            builderNewBuilder.setListValue(builderNewBuilder3);
        }
        return builderNewBuilder.build();
    }

    static final class StructOrError<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String errorDetail;
        private final T struct;

        private StructOrError(T t) {
            this.struct = (T) Preconditions.checkNotNull(t, "struct");
            this.errorDetail = null;
        }

        private StructOrError(String str) {
            this.struct = null;
            this.errorDetail = (String) Preconditions.checkNotNull(str, "errorDetail");
        }

        static <T> StructOrError<T> fromStruct(T t) {
            return new StructOrError<>(t);
        }

        static <T> StructOrError<T> fromError(String str) {
            return new StructOrError<>(str);
        }

        @Nullable
        String getErrorDetail() {
            return this.errorDetail;
        }

        @Nullable
        public T getStruct() {
            return this.struct;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StructOrError structOrError = (StructOrError) obj;
            return Objects.equals(this.errorDetail, structOrError.errorDetail) && Objects.equals(this.struct, structOrError.struct);
        }

        public int hashCode() {
            return Objects.hash(this.errorDetail, this.struct);
        }

        public String toString() {
            if (this.struct != null) {
                return MoreObjects.toStringHelper(this).add("struct", this.struct).toString();
            }
            return MoreObjects.toStringHelper(this).add("error", this.errorDetail).toString();
        }
    }

    public static final class Node {
        private final String buildVersion;
        private final List<String> clientFeatures;
        private final String cluster;
        private final String id;
        private final List<Address> listeningAddresses;

        @Nullable
        private final Locality locality;

        @Nullable
        private final Map<String, ?> metadata;
        private final String userAgentName;

        @Nullable
        private final String userAgentVersion;

        /* synthetic */ Node(String str, String str2, Map map, Locality locality, List list, String str3, String str4, String str5, List list2, AnonymousClass1 anonymousClass1) {
            this(str, str2, map, locality, list, str3, str4, str5, list2);
        }

        private Node(String str, String str2, @Nullable Map<String, ?> map, @Nullable Locality locality, List<Address> list, String str3, String str4, @Nullable String str5, List<String> list2) {
            this.id = (String) Preconditions.checkNotNull(str, "id");
            this.cluster = (String) Preconditions.checkNotNull(str2, "cluster");
            this.metadata = map;
            this.locality = locality;
            this.listeningAddresses = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "listeningAddresses"));
            this.buildVersion = (String) Preconditions.checkNotNull(str3, "buildVersion");
            this.userAgentName = (String) Preconditions.checkNotNull(str4, "userAgentName");
            this.userAgentVersion = str5;
            this.clientFeatures = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2, "clientFeatures"));
        }

        static Builder newBuilder() {
            return new Builder(null);
        }

        String getCluster() {
            return this.cluster;
        }

        String getId() {
            return this.id;
        }

        List<Address> getListeningAddresses() {
            return this.listeningAddresses;
        }

        @Nullable
        Locality getLocality() {
            return this.locality;
        }

        @Nullable
        Map<String, ?> getMetadata() {
            return this.metadata;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("id", this.id).add("cluster", this.cluster).add("metadata", this.metadata).add("locality", this.locality).add("listeningAddresses", this.listeningAddresses).add("buildVersion", this.buildVersion).add("userAgentName", this.userAgentName).add("userAgentVersion", this.userAgentVersion).add("clientFeatures", this.clientFeatures).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Node node = (Node) obj;
            return Objects.equals(this.id, node.id) && Objects.equals(this.cluster, node.cluster) && Objects.equals(this.metadata, node.metadata) && Objects.equals(this.locality, node.locality) && Objects.equals(this.listeningAddresses, node.listeningAddresses) && Objects.equals(this.buildVersion, node.buildVersion) && Objects.equals(this.userAgentName, node.userAgentName) && Objects.equals(this.userAgentVersion, node.userAgentVersion) && Objects.equals(this.clientFeatures, node.clientFeatures);
        }

        public int hashCode() {
            return Objects.hash(this.id, this.cluster, this.metadata, this.locality, this.listeningAddresses, this.buildVersion, this.userAgentName, this.userAgentVersion, this.clientFeatures);
        }

        Builder toBuilder() {
            Builder cluster = new Builder(null).setId(this.id).setCluster(this.cluster);
            Map<String, ?> map = this.metadata;
            if (map != null) {
                cluster.setMetadata(map);
            }
            Locality locality = this.locality;
            if (locality != null) {
                cluster.setLocality(locality);
            }
            cluster.listeningAddresses.addAll(this.listeningAddresses);
            return cluster;
        }

        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node toEnvoyProtoNode() {
            Node.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Node.newBuilder();
            builderNewBuilder.setId(this.id);
            builderNewBuilder.setCluster(this.cluster);
            if (this.metadata != null) {
                Struct.Builder builderNewBuilder2 = Struct.newBuilder();
                for (Map.Entry<String, ?> entry : this.metadata.entrySet()) {
                    builderNewBuilder2.putFields(entry.getKey(), EnvoyProtoData.convertToValue(entry.getValue()));
                }
                builderNewBuilder.setMetadata(builderNewBuilder2);
            }
            Locality locality = this.locality;
            if (locality != null) {
                builderNewBuilder.setLocality(locality.toEnvoyProtoLocality());
            }
            Iterator<Address> it2 = this.listeningAddresses.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addListeningAddresses(it2.next().toEnvoyProtoAddress());
            }
            builderNewBuilder.setUserAgentName(this.userAgentName);
            String str = this.userAgentVersion;
            if (str != null) {
                builderNewBuilder.setUserAgentVersion(str);
            }
            builderNewBuilder.addAllClientFeatures(this.clientFeatures);
            return builderNewBuilder.m23650build();
        }

        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node toEnvoyProtoNodeV2() {
            Node.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Node.newBuilder();
            builderNewBuilder.setId(this.id);
            builderNewBuilder.setCluster(this.cluster);
            if (this.metadata != null) {
                Struct.Builder builderNewBuilder2 = Struct.newBuilder();
                for (Map.Entry<String, ?> entry : this.metadata.entrySet()) {
                    builderNewBuilder2.putFields(entry.getKey(), EnvoyProtoData.convertToValue(entry.getValue()));
                }
                builderNewBuilder.setMetadata(builderNewBuilder2);
            }
            Locality locality = this.locality;
            if (locality != null) {
                builderNewBuilder.setLocality(locality.toEnvoyProtoLocalityV2());
            }
            Iterator<Address> it2 = this.listeningAddresses.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addListeningAddresses(it2.next().toEnvoyProtoAddressV2());
            }
            builderNewBuilder.setBuildVersion(this.buildVersion);
            builderNewBuilder.setUserAgentName(this.userAgentName);
            String str = this.userAgentVersion;
            if (str != null) {
                builderNewBuilder.setUserAgentVersion(str);
            }
            builderNewBuilder.addAllClientFeatures(this.clientFeatures);
            return builderNewBuilder.m16340build();
        }

        static final class Builder {
            private final List<String> clientFeatures;
            private final List<Address> listeningAddresses;
            private String buildVersion;
            private String cluster;
            private String id;
            @Nullable
            private Locality locality;

            @Nullable
            private Map<String, ?> metadata;
            private String userAgentName;

            @Nullable
            private String userAgentVersion;

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                this.id = "";
                this.cluster = "";
                this.listeningAddresses = new ArrayList();
                this.buildVersion = "";
                this.userAgentName = "";
                this.clientFeatures = new ArrayList();
            }

            Builder setId(String str) {
                this.id = (String) Preconditions.checkNotNull(str, "id");
                return this;
            }

            Builder setCluster(String str) {
                this.cluster = (String) Preconditions.checkNotNull(str, "cluster");
                return this;
            }

            Builder setMetadata(Map<String, ?> map) {
                this.metadata = (Map) Preconditions.checkNotNull(map, "metadata");
                return this;
            }

            Builder setLocality(Locality locality) {
                this.locality = (Locality) Preconditions.checkNotNull(locality, "locality");
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            Builder addListeningAddresses(Address address) {
                this.listeningAddresses.add(Preconditions.checkNotNull(address, "address"));
                return this;
            }

            Builder setBuildVersion(String str) {
                this.buildVersion = (String) Preconditions.checkNotNull(str, "buildVersion");
                return this;
            }

            Builder setUserAgentName(String str) {
                this.userAgentName = (String) Preconditions.checkNotNull(str, "userAgentName");
                return this;
            }

            Builder setUserAgentVersion(String str) {
                this.userAgentVersion = (String) Preconditions.checkNotNull(str, "userAgentVersion");
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            Builder addClientFeatures(String str) {
                this.clientFeatures.add(Preconditions.checkNotNull(str, "clientFeature"));
                return this;
            }

            Node build() {
                return new Node(this.id, this.cluster, this.metadata, this.locality, this.listeningAddresses, this.buildVersion, this.userAgentName, this.userAgentVersion, this.clientFeatures, null);
            }
        }
    }

    static final class Address {
        private final String address;
        private final int port;

        Address(String str, int i) {
            this.address = (String) Preconditions.checkNotNull(str, "address");
            this.port = i;
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address toEnvoyProtoAddress() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Address.newBuilder().setSocketAddress(SocketAddress.newBuilder().setAddress(this.address).setPortValue(this.port)).m21433build();
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address toEnvoyProtoAddressV2() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Address.newBuilder().setSocketAddress(io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.SocketAddress.newBuilder().setAddress(this.address).setPortValue(this.port)).m14262build();
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("address", this.address).add(RtspHeaders.Values.PORT, this.port).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Address address = (Address) obj;
            return this.port == address.port && Objects.equals(this.address, address.address);
        }

        public int hashCode() {
            return Objects.hash(this.address, Integer.valueOf(this.port));
        }
    }

    static final class Locality {
        private final String region;
        private final String subZone;
        private final String zone;

        Locality(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.region = str == null ? "" : str;
            this.zone = str2 == null ? "" : str2;
            this.subZone = str3 == null ? "" : str3;
        }

        static Locality fromEnvoyProtoLocality(io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality locality) {
            return new Locality(locality.getRegion(), locality.getZone(), locality.getSubZone());
        }

        static Locality fromEnvoyProtoLocalityV2(io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Locality locality) {
            return new Locality(locality.getRegion(), locality.getZone(), locality.getSubZone());
        }

        String getRegion() {
            return this.region;
        }

        String getSubZone() {
            return this.subZone;
        }

        String getZone() {
            return this.zone;
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality toEnvoyProtoLocality() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.config.core.v3.Locality.newBuilder().setRegion(this.region).setZone(this.zone).setSubZone(this.subZone).m23557build();
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Locality toEnvoyProtoLocalityV2() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.Locality.newBuilder().setRegion(this.region).setZone(this.zone).setSubZone(this.subZone).m16247build();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Locality locality = (Locality) obj;
            return Objects.equals(this.region, locality.region) && Objects.equals(this.zone, locality.zone) && Objects.equals(this.subZone, locality.subZone);
        }

        public int hashCode() {
            return Objects.hash(this.region, this.zone, this.subZone);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("region", this.region).add("zone", this.zone).add("subZone", this.subZone).toString();
        }
    }

    static final class LocalityLbEndpoints {
        private final List<LbEndpoint> endpoints;
        private final int localityWeight;
        private final int priority;

        LocalityLbEndpoints(List<LbEndpoint> list, int i, int i2) {
            this.endpoints = list;
            this.localityWeight = i;
            this.priority = i2;
        }

        static LocalityLbEndpoints fromEnvoyProtoLocalityLbEndpoints(io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LocalityLbEndpoints localityLbEndpoints) {
            ArrayList arrayList = new ArrayList(localityLbEndpoints.getLbEndpointsCount());
            Iterator<io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint> it2 = localityLbEndpoints.getLbEndpointsList().iterator();
            while (it2.hasNext()) {
                arrayList.add(LbEndpoint.fromEnvoyProtoLbEndpoint(it2.next()));
            }
            return new LocalityLbEndpoints(arrayList, localityLbEndpoints.getLoadBalancingWeight().getValue(), localityLbEndpoints.getPriority());
        }

        static LocalityLbEndpoints fromEnvoyProtoLocalityLbEndpointsV2(io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LocalityLbEndpoints localityLbEndpoints) {
            ArrayList arrayList = new ArrayList(localityLbEndpoints.getLbEndpointsCount());
            Iterator<io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LbEndpoint> it2 = localityLbEndpoints.getLbEndpointsList().iterator();
            while (it2.hasNext()) {
                arrayList.add(LbEndpoint.fromEnvoyProtoLbEndpointV2(it2.next()));
            }
            return new LocalityLbEndpoints(arrayList, localityLbEndpoints.getLoadBalancingWeight().getValue(), localityLbEndpoints.getPriority());
        }

        int getLocalityWeight() {
            return this.localityWeight;
        }

        int getPriority() {
            return this.priority;
        }

        List<LbEndpoint> getEndpoints() {
            return Collections.unmodifiableList(this.endpoints);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LocalityLbEndpoints localityLbEndpoints = (LocalityLbEndpoints) obj;
            return this.localityWeight == localityLbEndpoints.localityWeight && this.priority == localityLbEndpoints.priority && Objects.equals(this.endpoints, localityLbEndpoints.endpoints);
        }

        public int hashCode() {
            return Objects.hash(this.endpoints, Integer.valueOf(this.localityWeight), Integer.valueOf(this.priority));
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("endpoints", this.endpoints).add("localityWeight", this.localityWeight).add("priority", this.priority).toString();
        }
    }

    static final class LbEndpoint {
        private final EquivalentAddressGroup eag;
        private final boolean isHealthy;
        private final int loadBalancingWeight;

        LbEndpoint(String str, int i, int i2, boolean z) {
            this(new EquivalentAddressGroup(new InetSocketAddress(str, i)), i2, z);
        }

        LbEndpoint(EquivalentAddressGroup equivalentAddressGroup, int i, boolean z) {
            this.eag = equivalentAddressGroup;
            this.loadBalancingWeight = i;
            this.isHealthy = z;
        }

        static LbEndpoint fromEnvoyProtoLbEndpoint(io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.LbEndpoint lbEndpoint) {
            SocketAddress socketAddress = lbEndpoint.getEndpoint().getAddress().getSocketAddress();
            return new LbEndpoint(new EquivalentAddressGroup(ImmutableList.of(new InetSocketAddress(socketAddress.getAddress(), socketAddress.getPortValue()))), lbEndpoint.getLoadBalancingWeight().getValue(), lbEndpoint.getHealthStatus() == HealthStatus.HEALTHY || lbEndpoint.getHealthStatus() == HealthStatus.UNKNOWN);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LbEndpoint fromEnvoyProtoLbEndpointV2(io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.LbEndpoint lbEndpoint) {
            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.SocketAddress socketAddress = lbEndpoint.getEndpoint().getAddress().getSocketAddress();
            return new LbEndpoint(new EquivalentAddressGroup(ImmutableList.of(new InetSocketAddress(socketAddress.getAddress(), socketAddress.getPortValue()))), lbEndpoint.getLoadBalancingWeight().getValue(), lbEndpoint.getHealthStatus() == io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HealthStatus.HEALTHY || lbEndpoint.getHealthStatus() == io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HealthStatus.UNKNOWN);
        }

        EquivalentAddressGroup getAddress() {
            return this.eag;
        }

        int getLoadBalancingWeight() {
            return this.loadBalancingWeight;
        }

        boolean isHealthy() {
            return this.isHealthy;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LbEndpoint lbEndpoint = (LbEndpoint) obj;
            return this.loadBalancingWeight == lbEndpoint.loadBalancingWeight && Objects.equals(this.eag, lbEndpoint.eag) && this.isHealthy == lbEndpoint.isHealthy;
        }

        public int hashCode() {
            return Objects.hash(this.eag, Integer.valueOf(this.loadBalancingWeight), Boolean.valueOf(this.isHealthy));
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("eag", this.eag).add("loadBalancingWeight", this.loadBalancingWeight).add("isHealthy", this.isHealthy).toString();
        }
    }

    static final class DropOverload {
        private final String category;
        private final int dropsPerMillion;

        DropOverload(String str, int i) {
            this.category = str;
            this.dropsPerMillion = i;
        }

        static DropOverload fromEnvoyProtoDropOverload(ClusterLoadAssignment.Policy.DropOverload dropOverload) {
            FractionalPercent dropPercentage = dropOverload.getDropPercentage();
            int numerator = dropPercentage.getNumerator();
            int i = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType[dropPercentage.getDenominator().ordinal()];
            if (i == 1) {
                numerator *= 100;
            } else if (i == 2) {
                numerator *= 10000;
            } else if (i != 3) {
                throw new IllegalArgumentException("Unknown denominator type of " + dropPercentage);
            }
            if (numerator > 1000000) {
                numerator = DurationKt.NANOS_IN_MILLIS;
            }
            return new DropOverload(dropOverload.getCategory(), numerator);
        }

        static DropOverload fromEnvoyProtoDropOverloadV2(ClusterLoadAssignment.Policy.DropOverload dropOverload) {
            io.grpc.xds.shaded.io.envoyproxy.envoy.type.FractionalPercent dropPercentage = dropOverload.getDropPercentage();
            int numerator = dropPercentage.getNumerator();
            int i = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType[dropPercentage.getDenominator().ordinal()];
            if (i == 1) {
                numerator *= 100;
            } else if (i == 2) {
                numerator *= 10000;
            } else if (i != 3) {
                throw new IllegalArgumentException("Unknown denominator type of " + dropPercentage);
            }
            if (numerator > 1000000) {
                numerator = DurationKt.NANOS_IN_MILLIS;
            }
            return new DropOverload(dropOverload.getCategory(), numerator);
        }

        String getCategory() {
            return this.category;
        }

        int getDropsPerMillion() {
            return this.dropsPerMillion;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DropOverload dropOverload = (DropOverload) obj;
            return this.dropsPerMillion == dropOverload.dropsPerMillion && Objects.equals(this.category, dropOverload.category);
        }

        public int hashCode() {
            return Objects.hash(this.category, Integer.valueOf(this.dropsPerMillion));
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("category", this.category).add("dropsPerMillion", this.dropsPerMillion).toString();
        }
    }

    static final class Route {
        private final RouteAction routeAction;
        private final RouteMatch routeMatch;

        Route(RouteMatch routeMatch, @Nullable RouteAction routeAction) {
            this.routeMatch = routeMatch;
            this.routeAction = routeAction;
        }

        @Nullable
        static StructOrError<Route> fromEnvoyProtoRoute(io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Route route) {
            StructOrError<RouteMatch> structOrErrorConvertEnvoyProtoRouteMatch = convertEnvoyProtoRouteMatch(route.getMatch());
            if (structOrErrorConvertEnvoyProtoRouteMatch == null) {
                return null;
            }
            if (structOrErrorConvertEnvoyProtoRouteMatch.getErrorDetail() != null) {
                return StructOrError.fromError("Invalid route [" + route.getName() + "]: " + structOrErrorConvertEnvoyProtoRouteMatch.getErrorDetail());
            }
            int i = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase[route.getActionCase().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return StructOrError.fromError("Unsupported action type: redirect");
                }
                if (i == 3) {
                    return StructOrError.fromError("Unsupported action type: direct_response");
                }
                if (i == 4) {
                    return StructOrError.fromError("Unsupported action type: filter_action");
                }
                return StructOrError.fromError("Unknown action type: " + route.getActionCase());
            }
            StructOrError<RouteAction> structOrErrorFromEnvoyProtoRouteAction = RouteAction.fromEnvoyProtoRouteAction(route.getRoute());
            if (structOrErrorFromEnvoyProtoRouteAction == null) {
                return null;
            }
            if (structOrErrorFromEnvoyProtoRouteAction.getErrorDetail() != null) {
                return StructOrError.fromError("Invalid route [" + route.getName() + "]: " + structOrErrorFromEnvoyProtoRouteAction.getErrorDetail());
            }
            return StructOrError.fromStruct(new Route(structOrErrorConvertEnvoyProtoRouteMatch.getStruct(), structOrErrorFromEnvoyProtoRouteAction.getStruct()));
        }

        @Nullable
        static StructOrError<RouteMatch> convertEnvoyProtoRouteMatch(io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteMatch routeMatch) {
            RouteMatch.FractionMatcher struct = null;
            if (routeMatch.getQueryParametersCount() != 0) {
                return null;
            }
            if (routeMatch.hasCaseSensitive() && !routeMatch.getCaseSensitive().getValue()) {
                return StructOrError.fromError("Unsupported match option: case insensitive");
            }
            StructOrError<RouteMatch.PathMatcher> structOrErrorConvertEnvoyProtoPathMatcher = convertEnvoyProtoPathMatcher(routeMatch);
            if (structOrErrorConvertEnvoyProtoPathMatcher.getErrorDetail() != null) {
                return StructOrError.fromError(structOrErrorConvertEnvoyProtoPathMatcher.getErrorDetail());
            }
            if (routeMatch.hasRuntimeFraction()) {
                StructOrError<RouteMatch.FractionMatcher> structOrErrorConvertEnvoyProtoFraction = convertEnvoyProtoFraction(routeMatch.getRuntimeFraction().getDefaultValue());
                if (structOrErrorConvertEnvoyProtoFraction.getErrorDetail() != null) {
                    return StructOrError.fromError(structOrErrorConvertEnvoyProtoFraction.getErrorDetail());
                }
                struct = structOrErrorConvertEnvoyProtoFraction.getStruct();
            }
            ArrayList arrayList = new ArrayList();
            Iterator<HeaderMatcher> it2 = routeMatch.getHeadersList().iterator();
            while (it2.hasNext()) {
                StructOrError<RouteMatch.HeaderMatcher> structOrErrorConvertEnvoyProtoHeaderMatcher = convertEnvoyProtoHeaderMatcher(it2.next());
                if (structOrErrorConvertEnvoyProtoHeaderMatcher.getErrorDetail() != null) {
                    return StructOrError.fromError(structOrErrorConvertEnvoyProtoHeaderMatcher.getErrorDetail());
                }
                arrayList.add(structOrErrorConvertEnvoyProtoHeaderMatcher.getStruct());
            }
            return StructOrError.fromStruct(new RouteMatch(structOrErrorConvertEnvoyProtoPathMatcher.getStruct(), Collections.unmodifiableList(arrayList), struct));
        }

        private static StructOrError<RouteMatch.PathMatcher> convertEnvoyProtoPathMatcher(io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteMatch routeMatch) {
            String prefix;
            Pattern patternCompile;
            int i = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase[routeMatch.getPathSpecifierCase().ordinal()];
            String path = null;
            if (i == 1) {
                prefix = routeMatch.getPrefix();
                patternCompile = null;
            } else if (i == 2) {
                patternCompile = null;
                path = routeMatch.getPath();
                prefix = null;
            } else if (i == 3) {
                try {
                    patternCompile = Pattern.compile(routeMatch.getSafeRegex().getRegex());
                    prefix = null;
                } catch (PatternSyntaxException e) {
                    return StructOrError.fromError("Malformed safe regex pattern: " + e.getMessage());
                }
            } else {
                return StructOrError.fromError("Unknown path match type");
            }
            return StructOrError.fromStruct(new RouteMatch.PathMatcher(path, prefix, patternCompile));
        }

        private static StructOrError<RouteMatch.FractionMatcher> convertEnvoyProtoFraction(FractionalPercent fractionalPercent) {
            int i;
            int numerator = fractionalPercent.getNumerator();
            int i2 = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType[fractionalPercent.getDenominator().ordinal()];
            if (i2 == 1) {
                i = 10000;
            } else if (i2 == 2) {
                i = 100;
            } else {
                if (i2 != 3) {
                    return StructOrError.fromError("Unrecognized fractional percent denominator: " + fractionalPercent.getDenominator());
                }
                i = DurationKt.NANOS_IN_MILLIS;
            }
            return StructOrError.fromStruct(new RouteMatch.FractionMatcher(numerator, i));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v4 */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Type inference failed for: r6v7 */
        /* JADX WARN: Type inference failed for: r6v8, types: [io.grpc.xds.RouteMatch$HeaderMatcher$Range] */
        /* JADX WARN: Type inference failed for: r6v9 */
        /* JADX WARN: Type inference failed for: r7v5 */
        /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Boolean] */
        /* JADX WARN: Type inference failed for: r7v7 */
        /* JADX WARN: Type inference failed for: r7v8 */
        static StructOrError<RouteMatch.HeaderMatcher> convertEnvoyProtoHeaderMatcher(HeaderMatcher headerMatcher) {
            String exactMatch;
            Pattern patternCompile;
            ??range;
            String str;
            String prefixMatch;
            Object objValueOf;
            String suffixMatch;
            ??r7;
            switch (AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[headerMatcher.getHeaderMatchSpecifierCase().ordinal()]) {
                case 1:
                    exactMatch = headerMatcher.getExactMatch();
                    patternCompile = null;
                    range = null;
                    str = range;
                    prefixMatch = str;
                    objValueOf = str;
                    suffixMatch = prefixMatch;
                    r7 = objValueOf;
                    return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                case 2:
                    try {
                        patternCompile = Pattern.compile(headerMatcher.getSafeRegexMatch().getRegex());
                        exactMatch = null;
                        range = null;
                        str = range;
                        prefixMatch = str;
                        objValueOf = str;
                        suffixMatch = prefixMatch;
                        r7 = objValueOf;
                        return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                    } catch (PatternSyntaxException e) {
                        return StructOrError.fromError("HeaderMatcher [" + headerMatcher.getName() + "] contains malformed safe regex pattern: " + e.getMessage());
                    }
                case 3:
                    range = new RouteMatch.HeaderMatcher.Range(headerMatcher.getRangeMatch().getStart(), headerMatcher.getRangeMatch().getEnd());
                    exactMatch = null;
                    patternCompile = null;
                    str = null;
                    prefixMatch = str;
                    objValueOf = str;
                    suffixMatch = prefixMatch;
                    r7 = objValueOf;
                    return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                case 4:
                    objValueOf = Boolean.valueOf(headerMatcher.getPresentMatch());
                    exactMatch = null;
                    patternCompile = null;
                    range = null;
                    prefixMatch = null;
                    suffixMatch = prefixMatch;
                    r7 = objValueOf;
                    return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                case 5:
                    prefixMatch = headerMatcher.getPrefixMatch();
                    exactMatch = null;
                    patternCompile = null;
                    range = null;
                    r7 = 0;
                    suffixMatch = null;
                    return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                case 6:
                    suffixMatch = headerMatcher.getSuffixMatch();
                    exactMatch = null;
                    patternCompile = null;
                    range = null;
                    r7 = 0;
                    prefixMatch = null;
                    return StructOrError.fromStruct(new RouteMatch.HeaderMatcher(headerMatcher.getName(), exactMatch, patternCompile, range, r7, prefixMatch, suffixMatch, headerMatcher.getInvertMatch()));
                default:
                    return StructOrError.fromError("Unknown header matcher type");
            }
        }

        RouteAction getRouteAction() {
            return this.routeAction;
        }

        RouteMatch getRouteMatch() {
            return this.routeMatch;
        }

        boolean isDefaultRoute() {
            String prefix = this.routeMatch.getPathMatch().getPrefix();
            if (prefix != null) {
                return prefix.isEmpty() || prefix.equals("/");
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Route route = (Route) obj;
            return Objects.equals(this.routeMatch, route.routeMatch) && Objects.equals(this.routeAction, route.routeAction);
        }

        public int hashCode() {
            return Objects.hash(this.routeMatch, this.routeAction);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("routeMatch", this.routeMatch).add("routeAction", this.routeAction).toString();
        }
    }

    static final class RouteAction {

        @Nullable
        private final String cluster;
        private final long timeoutNano;

        @Nullable
        private final List<ClusterWeight> weightedClusters;

        RouteAction(long j, @Nullable String str, @Nullable List<ClusterWeight> list) {
            this.timeoutNano = j;
            this.cluster = str;
            this.weightedClusters = list;
        }

        @Nullable
        static StructOrError<RouteAction> fromEnvoyProtoRouteAction(io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.RouteAction routeAction) {
            ArrayList arrayList;
            int i = AnonymousClass1.$SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase[routeAction.getClusterSpecifierCase().ordinal()];
            String cluster = null;
            if (i == 1) {
                arrayList = null;
                cluster = routeAction.getCluster();
            } else {
                if (i == 2) {
                    return null;
                }
                if (i == 3) {
                    List<WeightedCluster.ClusterWeight> clustersList = routeAction.getWeightedClusters().getClustersList();
                    if (clustersList.isEmpty()) {
                        return StructOrError.fromError("No cluster found in weighted cluster list");
                    }
                    arrayList = new ArrayList();
                    Iterator<WeightedCluster.ClusterWeight> it2 = clustersList.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(ClusterWeight.fromEnvoyProtoClusterWeight(it2.next()));
                    }
                } else {
                    return StructOrError.fromError("Unknown cluster specifier: " + routeAction.getClusterSpecifierCase());
                }
            }
            long nanos = TimeUnit.SECONDS.toNanos(15L);
            if (routeAction.hasMaxGrpcTimeout()) {
                nanos = Durations.toNanos(routeAction.getMaxGrpcTimeout());
            } else if (routeAction.hasTimeout()) {
                nanos = Durations.toNanos(routeAction.getTimeout());
            }
            if (nanos == 0) {
                nanos = Long.MAX_VALUE;
            }
            return StructOrError.fromStruct(new RouteAction(nanos, cluster, arrayList));
        }

        @Nullable
        String getCluster() {
            return this.cluster;
        }

        @Nullable
        List<ClusterWeight> getWeightedCluster() {
            return this.weightedClusters;
        }

        Long getTimeoutNano() {
            return Long.valueOf(this.timeoutNano);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RouteAction routeAction = (RouteAction) obj;
            return Objects.equals(Long.valueOf(this.timeoutNano), Long.valueOf(routeAction.timeoutNano)) && Objects.equals(this.cluster, routeAction.cluster) && Objects.equals(this.weightedClusters, routeAction.weightedClusters);
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.timeoutNano), this.cluster, this.weightedClusters);
        }

        public String toString() {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
            stringHelper.add(RtspHeaders.Values.TIMEOUT, this.timeoutNano + "ns");
            String str = this.cluster;
            if (str != null) {
                stringHelper.add("cluster", str);
            }
            List<ClusterWeight> list = this.weightedClusters;
            if (list != null) {
                stringHelper.add("weightedClusters", list);
            }
            return stringHelper.toString();
        }
    }

    /* renamed from: io.grpc.xds.EnvoyProtoData$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType;

        static {
            int[] iArr = new int[RouteAction.ClusterSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase = iArr;
            try {
                iArr[RouteAction.ClusterSpecifierCase.CLUSTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase[RouteAction.ClusterSpecifierCase.CLUSTER_HEADER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase[RouteAction.ClusterSpecifierCase.WEIGHTED_CLUSTERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteAction$ClusterSpecifierCase[RouteAction.ClusterSpecifierCase.CLUSTERSPECIFIER_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[HeaderMatcher.HeaderMatchSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase = iArr2;
            try {
                iArr2[HeaderMatcher.HeaderMatchSpecifierCase.EXACT_MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.SAFE_REGEX_MATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.RANGE_MATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.PRESENT_MATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.PREFIX_MATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.SUFFIX_MATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$HeaderMatcher$HeaderMatchSpecifierCase[HeaderMatcher.HeaderMatchSpecifierCase.HEADERMATCHSPECIFIER_NOT_SET.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr3 = new int[RouteMatch.PathSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase = iArr3;
            try {
                iArr3[RouteMatch.PathSpecifierCase.PREFIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase[RouteMatch.PathSpecifierCase.PATH.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase[RouteMatch.PathSpecifierCase.SAFE_REGEX.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$RouteMatch$PathSpecifierCase[RouteMatch.PathSpecifierCase.PATHSPECIFIER_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr4 = new int[Route.ActionCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase = iArr4;
            try {
                iArr4[Route.ActionCase.ROUTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase[Route.ActionCase.REDIRECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase[Route.ActionCase.DIRECT_RESPONSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase[Route.ActionCase.FILTER_ACTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$config$route$v3$Route$ActionCase[Route.ActionCase.ACTION_NOT_SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            int[] iArr5 = new int[FractionalPercent.DenominatorType.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType = iArr5;
            try {
                iArr5[FractionalPercent.DenominatorType.TEN_THOUSAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.HUNDRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.MILLION.ordinal()] = 3;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.UNRECOGNIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr6 = new int[FractionalPercent.DenominatorType.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType = iArr6;
            try {
                iArr6[FractionalPercent.DenominatorType.TEN_THOUSAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.HUNDRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.MILLION.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$v3$FractionalPercent$DenominatorType[FractionalPercent.DenominatorType.UNRECOGNIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused28) {
            }
        }
    }

    static final class ClusterWeight {
        private final String name;
        private final int weight;

        ClusterWeight(String str, int i) {
            this.name = str;
            this.weight = i;
        }

        static ClusterWeight fromEnvoyProtoClusterWeight(WeightedCluster.ClusterWeight clusterWeight) {
            return new ClusterWeight(clusterWeight.getName(), clusterWeight.getWeight().getValue());
        }

        String getName() {
            return this.name;
        }

        int getWeight() {
            return this.weight;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClusterWeight clusterWeight = (ClusterWeight) obj;
            return this.weight == clusterWeight.weight && Objects.equals(this.name, clusterWeight.name);
        }

        public int hashCode() {
            return Objects.hash(this.name, Integer.valueOf(this.weight));
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("name", this.name).add("weight", this.weight).toString();
        }
    }

    static final class ClusterStats {
        private final String clusterName;

        @Nullable
        private final String clusterServiceName;
        private final List<DroppedRequests> droppedRequestsList;
        private final long loadReportIntervalNanos;
        private final long totalDroppedRequests;
        private final List<UpstreamLocalityStats> upstreamLocalityStatsList;

        /* synthetic */ ClusterStats(String str, String str2, List list, List list2, long j, long j2, AnonymousClass1 anonymousClass1) {
            this(str, str2, list, list2, j, j2);
        }

        private ClusterStats(String str, @Nullable String str2, List<UpstreamLocalityStats> list, List<DroppedRequests> list2, long j, long j2) {
            this.clusterName = (String) Preconditions.checkNotNull(str, "clusterName");
            this.clusterServiceName = str2;
            this.upstreamLocalityStatsList = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "upstreamLocalityStatsList"));
            this.droppedRequestsList = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2, "dropRequestsList"));
            this.totalDroppedRequests = j;
            this.loadReportIntervalNanos = j2;
        }

        static Builder newBuilder() {
            return new Builder(null);
        }

        String getClusterName() {
            return this.clusterName;
        }

        @Nullable
        String getClusterServiceName() {
            return this.clusterServiceName;
        }

        List<DroppedRequests> getDroppedRequestsList() {
            return this.droppedRequestsList;
        }

        long getLoadReportIntervalNanos() {
            return this.loadReportIntervalNanos;
        }

        long getTotalDroppedRequests() {
            return this.totalDroppedRequests;
        }

        List<UpstreamLocalityStats> getUpstreamLocalityStatsList() {
            return this.upstreamLocalityStatsList;
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterStats toEnvoyProtoClusterStats() {
            ClusterStats.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.ClusterStats.newBuilder();
            builderNewBuilder.setClusterName(this.clusterName);
            String str = this.clusterServiceName;
            if (str != null) {
                builderNewBuilder.setClusterServiceName(str);
            }
            Iterator<UpstreamLocalityStats> it2 = this.upstreamLocalityStatsList.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addUpstreamLocalityStats(it2.next().toEnvoyProtoUpstreamLocalityStats());
            }
            Iterator<DroppedRequests> it3 = this.droppedRequestsList.iterator();
            while (it3.hasNext()) {
                builderNewBuilder.addDroppedRequests(it3.next().toEnvoyProtoDroppedRequests());
            }
            builderNewBuilder.setTotalDroppedRequests(this.totalDroppedRequests);
            builderNewBuilder.setLoadReportInterval(Durations.fromNanos(this.loadReportIntervalNanos));
            return builderNewBuilder.m24668build();
        }

        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStats toEnvoyProtoClusterStatsV2() {
            ClusterStats.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.ClusterStats.newBuilder();
            builderNewBuilder.setClusterName(this.clusterName);
            Iterator<UpstreamLocalityStats> it2 = this.upstreamLocalityStatsList.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addUpstreamLocalityStats(it2.next().toEnvoyProtoUpstreamLocalityStatsV2());
            }
            Iterator<DroppedRequests> it3 = this.droppedRequestsList.iterator();
            while (it3.hasNext()) {
                builderNewBuilder.addDroppedRequests(it3.next().toEnvoyProtoDroppedRequestsV2());
            }
            builderNewBuilder.setTotalDroppedRequests(this.totalDroppedRequests);
            builderNewBuilder.setLoadReportInterval(Durations.fromNanos(this.loadReportIntervalNanos));
            return builderNewBuilder.m17081build();
        }

        Builder toBuilder() {
            Builder loadReportIntervalNanos = new Builder(null).setClusterName(this.clusterName).setTotalDroppedRequests(this.totalDroppedRequests).setLoadReportIntervalNanos(this.loadReportIntervalNanos);
            String str = this.clusterServiceName;
            if (str != null) {
                loadReportIntervalNanos.setClusterServiceName(str);
            }
            Iterator<UpstreamLocalityStats> it2 = this.upstreamLocalityStatsList.iterator();
            while (it2.hasNext()) {
                loadReportIntervalNanos.addUpstreamLocalityStats(it2.next());
            }
            Iterator<DroppedRequests> it3 = this.droppedRequestsList.iterator();
            while (it3.hasNext()) {
                loadReportIntervalNanos.addDroppedRequests(it3.next());
            }
            return loadReportIntervalNanos;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClusterStats clusterStats = (ClusterStats) obj;
            return this.totalDroppedRequests == clusterStats.totalDroppedRequests && this.loadReportIntervalNanos == clusterStats.loadReportIntervalNanos && Objects.equals(this.clusterName, clusterStats.clusterName) && Objects.equals(this.clusterServiceName, clusterStats.clusterServiceName) && Objects.equals(this.upstreamLocalityStatsList, clusterStats.upstreamLocalityStatsList) && Objects.equals(this.droppedRequestsList, clusterStats.droppedRequestsList);
        }

        public int hashCode() {
            return Objects.hash(this.clusterName, this.clusterServiceName, this.upstreamLocalityStatsList, this.droppedRequestsList, Long.valueOf(this.totalDroppedRequests), Long.valueOf(this.loadReportIntervalNanos));
        }

        static final class Builder {
            private final List<DroppedRequests> droppedRequestsList;
            private final List<UpstreamLocalityStats> upstreamLocalityStatsList;
            private String clusterName;
            private String clusterServiceName;
            private long loadReportIntervalNanos;
            private long totalDroppedRequests;

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                this.upstreamLocalityStatsList = new ArrayList();
                this.droppedRequestsList = new ArrayList();
            }

            Builder setLoadReportIntervalNanos(long j) {
                this.loadReportIntervalNanos = j;
                return this;
            }

            Builder setTotalDroppedRequests(long j) {
                this.totalDroppedRequests = j;
                return this;
            }

            Builder setClusterName(String str) {
                this.clusterName = (String) Preconditions.checkNotNull(str, "clusterName");
                return this;
            }

            Builder setClusterServiceName(String str) {
                this.clusterServiceName = (String) Preconditions.checkNotNull(str, "clusterServiceName");
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            Builder addUpstreamLocalityStats(UpstreamLocalityStats upstreamLocalityStats) {
                this.upstreamLocalityStatsList.add(Preconditions.checkNotNull(upstreamLocalityStats, "upstreamLocalityStats"));
                return this;
            }

            Builder addAllUpstreamLocalityStats(Collection<UpstreamLocalityStats> collection) {
                this.upstreamLocalityStatsList.addAll(collection);
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            Builder addDroppedRequests(DroppedRequests droppedRequests) {
                this.droppedRequestsList.add(Preconditions.checkNotNull(droppedRequests, "dropRequests"));
                return this;
            }

            ClusterStats build() {
                return new ClusterStats(this.clusterName, this.clusterServiceName, this.upstreamLocalityStatsList, this.droppedRequestsList, this.totalDroppedRequests, this.loadReportIntervalNanos, null);
            }
        }

        static final class DroppedRequests {
            private final String category;
            private final long droppedCount;

            DroppedRequests(String str, long j) {
                this.category = (String) Preconditions.checkNotNull(str, "category");
                this.droppedCount = j;
            }

            String getCategory() {
                return this.category;
            }

            long getDroppedCount() {
                return this.droppedCount;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ClusterStats.DroppedRequests toEnvoyProtoDroppedRequests() {
                return ClusterStats.DroppedRequests.newBuilder().setCategory(this.category).setDroppedCount(this.droppedCount).m24714build();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ClusterStats.DroppedRequests toEnvoyProtoDroppedRequestsV2() {
                return ClusterStats.DroppedRequests.newBuilder().setCategory(this.category).setDroppedCount(this.droppedCount).m17127build();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                DroppedRequests droppedRequests = (DroppedRequests) obj;
                return this.droppedCount == droppedRequests.droppedCount && Objects.equals(this.category, droppedRequests.category);
            }

            public int hashCode() {
                return Objects.hash(this.category, Long.valueOf(this.droppedCount));
            }
        }
    }

    static final class UpstreamLocalityStats {
        private final List<EndpointLoadMetricStats> loadMetricStatsList;
        private final Locality locality;
        private final long totalErrorRequests;
        private final long totalIssuedRequests;
        private final long totalRequestsInProgress;
        private final long totalSuccessfulRequests;

        /* synthetic */ UpstreamLocalityStats(Locality locality, long j, long j2, long j3, long j4, List list, AnonymousClass1 anonymousClass1) {
            this(locality, j, j2, j3, j4, list);
        }

        private UpstreamLocalityStats(Locality locality, long j, long j2, long j3, long j4, List<EndpointLoadMetricStats> list) {
            this.locality = (Locality) Preconditions.checkNotNull(locality, "locality");
            this.totalSuccessfulRequests = j;
            this.totalErrorRequests = j2;
            this.totalRequestsInProgress = j3;
            this.totalIssuedRequests = j4;
            this.loadMetricStatsList = Collections.unmodifiableList((List) Preconditions.checkNotNull(list, "loadMetricStatsList"));
        }

        static Builder newBuilder() {
            return new Builder(null);
        }

        List<EndpointLoadMetricStats> getLoadMetricStatsList() {
            return this.loadMetricStatsList;
        }

        Locality getLocality() {
            return this.locality;
        }

        long getTotalErrorRequests() {
            return this.totalErrorRequests;
        }

        long getTotalIssuedRequests() {
            return this.totalIssuedRequests;
        }

        long getTotalRequestsInProgress() {
            return this.totalRequestsInProgress;
        }

        long getTotalSuccessfulRequests() {
            return this.totalSuccessfulRequests;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.UpstreamLocalityStats toEnvoyProtoUpstreamLocalityStats() {
            UpstreamLocalityStats.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.UpstreamLocalityStats.newBuilder();
            builderNewBuilder.setLocality(this.locality.toEnvoyProtoLocality()).setTotalSuccessfulRequests(this.totalSuccessfulRequests).setTotalErrorRequests(this.totalErrorRequests).setTotalRequestsInProgress(this.totalRequestsInProgress).setTotalIssuedRequests(this.totalIssuedRequests);
            Iterator<EndpointLoadMetricStats> it2 = this.loadMetricStatsList.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addLoadMetricStats(it2.next().toEnvoyProtoEndpointLoadMetricStats());
            }
            return builderNewBuilder.m25036build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.UpstreamLocalityStats toEnvoyProtoUpstreamLocalityStatsV2() {
            UpstreamLocalityStats.Builder builderNewBuilder = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.UpstreamLocalityStats.newBuilder();
            builderNewBuilder.setLocality(this.locality.toEnvoyProtoLocalityV2()).setTotalSuccessfulRequests(this.totalSuccessfulRequests).setTotalErrorRequests(this.totalErrorRequests).setTotalRequestsInProgress(this.totalRequestsInProgress).setTotalIssuedRequests(this.totalIssuedRequests);
            Iterator<EndpointLoadMetricStats> it2 = this.loadMetricStatsList.iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addLoadMetricStats(it2.next().toEnvoyProtoEndpointLoadMetricStatsV2());
            }
            return builderNewBuilder.m17449build();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpstreamLocalityStats upstreamLocalityStats = (UpstreamLocalityStats) obj;
            return this.totalSuccessfulRequests == upstreamLocalityStats.totalSuccessfulRequests && this.totalErrorRequests == upstreamLocalityStats.totalErrorRequests && this.totalRequestsInProgress == upstreamLocalityStats.totalRequestsInProgress && this.totalIssuedRequests == upstreamLocalityStats.totalIssuedRequests && Objects.equals(this.locality, upstreamLocalityStats.locality) && Objects.equals(this.loadMetricStatsList, upstreamLocalityStats.loadMetricStatsList);
        }

        public int hashCode() {
            return Objects.hash(this.locality, Long.valueOf(this.totalSuccessfulRequests), Long.valueOf(this.totalErrorRequests), Long.valueOf(this.totalRequestsInProgress), Long.valueOf(this.totalIssuedRequests), this.loadMetricStatsList);
        }

        static final class Builder {
            private final List<EndpointLoadMetricStats> loadMetricStatsList;
            private Locality locality;
            private long totalErrorRequests;
            private long totalIssuedRequests;
            private long totalRequestsInProgress;
            private long totalSuccessfulRequests;

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                this.loadMetricStatsList = new ArrayList();
            }

            Builder setTotalErrorRequests(long j) {
                this.totalErrorRequests = j;
                return this;
            }

            Builder setTotalIssuedRequests(long j) {
                this.totalIssuedRequests = j;
                return this;
            }

            Builder setTotalRequestsInProgress(long j) {
                this.totalRequestsInProgress = j;
                return this;
            }

            Builder setTotalSuccessfulRequests(long j) {
                this.totalSuccessfulRequests = j;
                return this;
            }

            Builder setLocality(Locality locality) {
                this.locality = (Locality) Preconditions.checkNotNull(locality, "locality");
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            Builder addLoadMetricStats(EndpointLoadMetricStats endpointLoadMetricStats) {
                this.loadMetricStatsList.add(Preconditions.checkNotNull(endpointLoadMetricStats, "endpointLoadMetricStats"));
                return this;
            }

            Builder addAllLoadMetricStats(Collection<EndpointLoadMetricStats> collection) {
                this.loadMetricStatsList.addAll((Collection) Preconditions.checkNotNull(collection, "endpointLoadMetricStats"));
                return this;
            }

            UpstreamLocalityStats build() {
                return new UpstreamLocalityStats(this.locality, this.totalSuccessfulRequests, this.totalErrorRequests, this.totalRequestsInProgress, this.totalIssuedRequests, this.loadMetricStatsList, null);
            }
        }
    }

    static final class EndpointLoadMetricStats {
        private final String metricName;
        private final long numRequestsFinishedWithMetric;
        private final double totalMetricValue;

        /* synthetic */ EndpointLoadMetricStats(String str, long j, double d, AnonymousClass1 anonymousClass1) {
            this(str, j, d);
        }

        private EndpointLoadMetricStats(String str, long j, double d) {
            this.metricName = (String) Preconditions.checkNotNull(str, "metricName");
            this.numRequestsFinishedWithMetric = j;
            this.totalMetricValue = d;
        }

        static Builder newBuilder() {
            return new Builder(null);
        }

        String getMetricName() {
            return this.metricName;
        }

        long getNumRequestsFinishedWithMetric() {
            return this.numRequestsFinishedWithMetric;
        }

        double getTotalMetricValue() {
            return this.totalMetricValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.EndpointLoadMetricStats toEnvoyProtoEndpointLoadMetricStats() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.config.endpoint.v3.EndpointLoadMetricStats.newBuilder().setMetricName(this.metricName).setNumRequestsFinishedWithMetric(this.numRequestsFinishedWithMetric).setTotalMetricValue(this.totalMetricValue).m24852build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.EndpointLoadMetricStats toEnvoyProtoEndpointLoadMetricStatsV2() {
            return io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.endpoint.EndpointLoadMetricStats.newBuilder().setMetricName(this.metricName).setNumRequestsFinishedWithMetric(this.numRequestsFinishedWithMetric).setTotalMetricValue(this.totalMetricValue).m17265build();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EndpointLoadMetricStats endpointLoadMetricStats = (EndpointLoadMetricStats) obj;
            return this.numRequestsFinishedWithMetric == endpointLoadMetricStats.numRequestsFinishedWithMetric && Double.compare(endpointLoadMetricStats.totalMetricValue, this.totalMetricValue) == 0 && Objects.equals(this.metricName, endpointLoadMetricStats.metricName);
        }

        public int hashCode() {
            return Objects.hash(this.metricName, Long.valueOf(this.numRequestsFinishedWithMetric), Double.valueOf(this.totalMetricValue));
        }

        static final class Builder {
            private String metricName;
            private long numRequestsFinishedWithMetric;
            private double totalMetricValue;

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
            }

            Builder setNumRequestsFinishedWithMetric(long j) {
                this.numRequestsFinishedWithMetric = j;
                return this;
            }

            Builder setTotalMetricValue(double d) {
                this.totalMetricValue = d;
                return this;
            }

            Builder setMetricName(String str) {
                this.metricName = (String) Preconditions.checkNotNull(str, "metricName");
                return this;
            }

            EndpointLoadMetricStats build() {
                return new EndpointLoadMetricStats(this.metricName, this.numRequestsFinishedWithMetric, this.totalMetricValue, null);
            }
        }
    }
}
