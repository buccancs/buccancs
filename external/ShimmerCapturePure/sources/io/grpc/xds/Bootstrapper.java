package io.grpc.xds;

import com.google.common.base.Preconditions;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.JsonParser;
import io.grpc.internal.JsonUtil;
import io.grpc.xds.EnvoyProtoData;
import io.grpc.xds.XdsLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class Bootstrapper {
    static final String CLIENT_FEATURE_DISABLE_OVERPROVISIONING = "envoy.lb.does_not_support_overprovisioning";
    private static final String BOOTSTRAP_PATH_SYS_ENV_VAR = "GRPC_XDS_BOOTSTRAP";
    private static final String LOG_PREFIX = "xds-bootstrap";
    private static final Bootstrapper DEFAULT_INSTANCE = new Bootstrapper() { // from class: io.grpc.xds.Bootstrapper.1
        @Override // io.grpc.xds.Bootstrapper
        public BootstrapInfo readBootstrap() throws IOException {
            String str = System.getenv(Bootstrapper.BOOTSTRAP_PATH_SYS_ENV_VAR);
            if (str == null) {
                throw new IOException("Environment variable GRPC_XDS_BOOTSTRAP not defined.");
            }
            XdsLogger.withPrefix(Bootstrapper.LOG_PREFIX).log(XdsLogger.XdsLogLevel.INFO, "GRPC_XDS_BOOTSTRAP={0}", str);
            return parseConfig(new String(Files.readAllBytes(Paths.get(str, new String[0])), StandardCharsets.UTF_8));
        }
    };

    public static Bootstrapper getInstance() {
        return DEFAULT_INSTANCE;
    }

    public static BootstrapInfo parseConfig(String str) throws IOException {
        HashMap map;
        XdsLogger xdsLoggerWithPrefix = XdsLogger.withPrefix(LOG_PREFIX);
        xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Reading bootstrap information");
        Map map2 = (Map) JsonParser.parse(str);
        xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.DEBUG, "Bootstrap configuration:\n{0}", map2);
        ArrayList arrayList = new ArrayList();
        List<?> list = JsonUtil.getList(map2, "xds_servers");
        if (list == null) {
            throw new IOException("Invalid bootstrap: 'xds_servers' does not exist.");
        }
        xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Configured with {0} xDS servers", Integer.valueOf(list.size()));
        for (Map<String, ?> map3 : JsonUtil.checkObjectList(list)) {
            String string = JsonUtil.getString(map3, "server_uri");
            if (string == null) {
                throw new IOException("Invalid bootstrap: 'xds_servers' contains unknown server.");
            }
            xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "xDS server URI: {0}", string);
            ArrayList arrayList2 = new ArrayList();
            List<?> list2 = JsonUtil.getList(map3, "channel_creds");
            if (list2 != null) {
                for (Map<String, ?> map4 : JsonUtil.checkObjectList(list2)) {
                    String string2 = JsonUtil.getString(map4, "type");
                    if (string2 == null) {
                        throw new IOException("Invalid bootstrap: 'xds_servers' contains server with unknown type 'channel_creds'.");
                    }
                    xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Channel credentials option: {0}", string2);
                    arrayList2.add(new ChannelCreds(string2, JsonUtil.getObject(map4, "config")));
                }
            }
            List<String> listOfStrings = JsonUtil.getListOfStrings(map3, "server_features");
            if (listOfStrings != null) {
                xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Server features: {0}", listOfStrings);
            }
            arrayList.add(new ServerInfo(string, arrayList2, listOfStrings));
        }
        EnvoyProtoData.Node.Builder builderNewBuilder = EnvoyProtoData.Node.newBuilder();
        Map<String, ?> object = JsonUtil.getObject(map2, "node");
        if (object != null) {
            String string3 = JsonUtil.getString(object, "id");
            if (string3 != null) {
                xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Node id: {0}", string3);
                builderNewBuilder.setId(string3);
            }
            String string4 = JsonUtil.getString(object, "cluster");
            if (string4 != null) {
                xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Node cluster: {0}", string4);
                builderNewBuilder.setCluster(string4);
            }
            Map<String, ?> object2 = JsonUtil.getObject(object, "metadata");
            if (object2 != null) {
                builderNewBuilder.setMetadata(object2);
            }
            Map<String, ?> object3 = JsonUtil.getObject(object, "locality");
            if (object3 != null) {
                String string5 = JsonUtil.getString(object3, "region");
                String string6 = JsonUtil.getString(object3, "zone");
                String string7 = JsonUtil.getString(object3, "sub_zone");
                if (string5 != null) {
                    xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Locality region: {0}", string5);
                }
                if (object3.containsKey("zone")) {
                    xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Locality zone: {0}", string6);
                }
                if (object3.containsKey("sub_zone")) {
                    xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Locality sub_zone: {0}", string7);
                }
                builderNewBuilder.setLocality(new EnvoyProtoData.Locality(string5, string6, string7));
            }
        }
        GrpcUtil.GrpcBuildVersion grpcBuildVersion = GrpcUtil.getGrpcBuildVersion();
        xdsLoggerWithPrefix.log(XdsLogger.XdsLogLevel.INFO, "Build version: {0}", grpcBuildVersion);
        builderNewBuilder.setBuildVersion(grpcBuildVersion.toString());
        builderNewBuilder.setUserAgentName(grpcBuildVersion.getUserAgent());
        builderNewBuilder.setUserAgentVersion(grpcBuildVersion.getImplementationVersion());
        builderNewBuilder.addClientFeatures(CLIENT_FEATURE_DISABLE_OVERPROVISIONING);
        Map<String, ?> object4 = JsonUtil.getObject(map2, "certificate_providers");
        if (object4 != null) {
            map = new HashMap(object4.size());
            for (String str2 : object4.keySet()) {
                Map<String, ?> object5 = JsonUtil.getObject(object4, str2);
                map.put(str2, new CertificateProviderInfo((String) checkForNull(JsonUtil.getString(object5, "plugin_name"), "plugin_name"), (Map) checkForNull(JsonUtil.getObject(object5, "config"), "config")));
            }
        } else {
            map = null;
        }
        return new BootstrapInfo(arrayList, builderNewBuilder.build(), map);
    }

    static <T> T checkForNull(T t, String str) throws IOException {
        if (t != null) {
            return t;
        }
        throw new IOException("Invalid bootstrap: '" + str + "' does not exist.");
    }

    public abstract BootstrapInfo readBootstrap() throws IOException;

    static class ChannelCreds {

        @Nullable
        private final Map<String, ?> config;
        private final String type;

        ChannelCreds(String str, @Nullable Map<String, ?> map) {
            this.type = str;
            this.config = map;
        }

        String getType() {
            return this.type;
        }

        @Nullable
        Map<String, ?> getConfig() {
            Map<String, ?> map = this.config;
            if (map != null) {
                return Collections.unmodifiableMap(map);
            }
            return null;
        }
    }

    static class ServerInfo {
        private final List<ChannelCreds> channelCredsList;

        @Nullable
        private final List<String> serverFeatures;
        private final String serverUri;

        ServerInfo(String str, List<ChannelCreds> list, List<String> list2) {
            this.serverUri = str;
            this.channelCredsList = list;
            this.serverFeatures = list2;
        }

        String getServerUri() {
            return this.serverUri;
        }

        List<ChannelCreds> getChannelCredentials() {
            return Collections.unmodifiableList(this.channelCredsList);
        }

        List<String> getServerFeatures() {
            List<String> list = this.serverFeatures;
            if (list == null) {
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(list);
        }
    }

    public static class CertificateProviderInfo {
        private final Map<String, ?> config;
        private final String pluginName;

        CertificateProviderInfo(String str, Map<String, ?> map) {
            this.pluginName = (String) Preconditions.checkNotNull(str, "pluginName");
            this.config = (Map) Preconditions.checkNotNull(map, "config");
        }

        public Map<String, ?> getConfig() {
            return this.config;
        }

        public String getPluginName() {
            return this.pluginName;
        }
    }

    public static class BootstrapInfo {

        @Nullable
        private final Map<String, CertificateProviderInfo> certProviders;
        private final EnvoyProtoData.Node node;
        private List<ServerInfo> servers;

        BootstrapInfo(List<ServerInfo> list, EnvoyProtoData.Node node, Map<String, CertificateProviderInfo> map) {
            this.servers = list;
            this.node = node;
            this.certProviders = map;
        }

        public EnvoyProtoData.Node getNode() {
            return this.node;
        }

        List<ServerInfo> getServers() {
            return Collections.unmodifiableList(this.servers);
        }

        public Map<String, CertificateProviderInfo> getCertProviders() {
            return Collections.unmodifiableMap(this.certProviders);
        }
    }
}
