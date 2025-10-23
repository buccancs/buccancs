package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.re2j.Pattern;
import com.google.re2j.PatternSyntaxException;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.JsonUtil;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.xds.RouteMatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public final class XdsRoutingLoadBalancerProvider extends LoadBalancerProvider {

    @Nullable
    private final LoadBalancerRegistry lbRegistry;

    public XdsRoutingLoadBalancerProvider() {
        this(null);
    }

    XdsRoutingLoadBalancerProvider(@Nullable LoadBalancerRegistry loadBalancerRegistry) {
        this.lbRegistry = loadBalancerRegistry;
    }

    private static ServiceConfigUtil.PolicySelection parseAction(Map<String, ?> map, LoadBalancerRegistry loadBalancerRegistry) {
        List<ServiceConfigUtil.LbConfig> listUnwrapLoadBalancingConfigList = ServiceConfigUtil.unwrapLoadBalancingConfigList(JsonUtil.getListOfObjects(map, "childPolicy"));
        if (listUnwrapLoadBalancingConfigList == null || listUnwrapLoadBalancingConfigList.isEmpty()) {
            throw new RuntimeException("childPolicy not specified");
        }
        NameResolver.ConfigOrError configOrErrorSelectLbPolicyFromList = ServiceConfigUtil.selectLbPolicyFromList(listUnwrapLoadBalancingConfigList, loadBalancerRegistry);
        if (configOrErrorSelectLbPolicyFromList.getError() != null) {
            throw configOrErrorSelectLbPolicyFromList.getError().asRuntimeException();
        }
        return (ServiceConfigUtil.PolicySelection) configOrErrorSelectLbPolicyFromList.getConfig();
    }

    private static Route parseRoute(Map<String, ?> map) {
        Pattern patternCompile;
        try {
            String string = JsonUtil.getString(map, "path");
            String string2 = JsonUtil.getString(map, "prefix");
            String string3 = JsonUtil.getString(map, "regex");
            if (string3 != null) {
                try {
                    patternCompile = Pattern.compile(string3);
                } catch (PatternSyntaxException e) {
                    throw new RuntimeException(e);
                }
            } else {
                patternCompile = null;
            }
            if (!isOneOf(string, string2, patternCompile)) {
                throw new RuntimeException("must specify exactly one patch match type");
            }
            RouteMatch.PathMatcher pathMatcher = new RouteMatch.PathMatcher(string, string2, patternCompile);
            ArrayList arrayList = new ArrayList();
            List<Map<String, ?>> listOfObjects = JsonUtil.getListOfObjects(map, "headers");
            if (listOfObjects != null) {
                Iterator<Map<String, ?>> it2 = listOfObjects.iterator();
                while (it2.hasNext()) {
                    arrayList.add(parseHeaderMatcher(it2.next()));
                }
            }
            Map<String, ?> object = JsonUtil.getObject(map, "matchFraction");
            RouteMatch.FractionMatcher fractionMatcher = object != null ? parseFractionMatcher(object) : null;
            String string4 = JsonUtil.getString(map, "action");
            if (string4 == null) {
                throw new RuntimeException("action name not specified");
            }
            return new Route(new RouteMatch(pathMatcher, arrayList, fractionMatcher), string4);
        } catch (RuntimeException e2) {
            throw new RuntimeException("Failed to parse Route: " + e2.getMessage());
        }
    }

    private static RouteMatch.HeaderMatcher parseHeaderMatcher(Map<String, ?> map) {
        Pattern patternCompile;
        try {
            String string = JsonUtil.getString(map, "name");
            if (string == null) {
                throw new RuntimeException("header name not specified");
            }
            String string2 = JsonUtil.getString(map, "exactMatch");
            String string3 = JsonUtil.getString(map, "regexMatch");
            if (string3 != null) {
                try {
                    patternCompile = Pattern.compile(string3);
                } catch (PatternSyntaxException e) {
                    throw new RuntimeException(e);
                }
            } else {
                patternCompile = null;
            }
            Map<String, ?> object = JsonUtil.getObject(map, "rangeMatch");
            RouteMatch.HeaderMatcher.Range headerRange = object == null ? null : parseHeaderRange(object);
            Boolean bool = JsonUtil.getBoolean(map, "presentMatch");
            String string4 = JsonUtil.getString(map, "prefixMatch");
            String string5 = JsonUtil.getString(map, "suffixMatch");
            if (!isOneOf(string2, patternCompile, headerRange, bool, string4, string5)) {
                throw new RuntimeException("must specify exactly one match type");
            }
            Boolean bool2 = JsonUtil.getBoolean(map, "invertMatch");
            return new RouteMatch.HeaderMatcher(string, string2, patternCompile, headerRange, bool, string4, string5, bool2 == null ? false : bool2.booleanValue());
        } catch (RuntimeException e2) {
            throw new RuntimeException("Failed to parse HeaderMatcher: " + e2.getMessage());
        }
    }

    private static boolean isOneOf(Object... objArr) {
        int i = 0;
        for (Object obj : objArr) {
            if (obj != null) {
                i++;
            }
        }
        return i == 1;
    }

    private static RouteMatch.HeaderMatcher.Range parseHeaderRange(Map<String, ?> map) {
        try {
            Long numberAsLong = JsonUtil.getNumberAsLong(map, "start");
            if (numberAsLong == null) {
                throw new RuntimeException("start not specified");
            }
            Long numberAsLong2 = JsonUtil.getNumberAsLong(map, "end");
            if (numberAsLong2 == null) {
                throw new RuntimeException("end not specified");
            }
            return new RouteMatch.HeaderMatcher.Range(numberAsLong.longValue(), numberAsLong2.longValue());
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to parse Range: " + e.getMessage());
        }
    }

    private static RouteMatch.FractionMatcher parseFractionMatcher(Map<String, ?> map) {
        try {
            Integer numberAsInteger = JsonUtil.getNumberAsInteger(map, "numerator");
            if (numberAsInteger == null) {
                throw new RuntimeException("numerator not specified");
            }
            Integer numberAsInteger2 = JsonUtil.getNumberAsInteger(map, "denominator");
            if (numberAsInteger2 == null) {
                throw new RuntimeException("denominator not specified");
            }
            return new RouteMatch.FractionMatcher(numberAsInteger.intValue(), numberAsInteger2.intValue());
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to parse Fraction: " + e.getMessage());
        }
    }

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return "xds_routing_experimental";
    }

    @Override // io.grpc.LoadBalancerProvider
    public int getPriority() {
        return 5;
    }

    @Override // io.grpc.LoadBalancerProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new XdsRoutingLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        try {
            Map<String, ?> object = JsonUtil.getObject(map, "action");
            if (object != null && !object.isEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (String str : object.keySet()) {
                    Map<String, ?> object2 = JsonUtil.getObject(object, str);
                    if (object2 == null) {
                        return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No config for action " + str + " in xds_routing LB policy: " + map));
                    }
                    LoadBalancerRegistry defaultRegistry = this.lbRegistry;
                    if (defaultRegistry == null) {
                        defaultRegistry = LoadBalancerRegistry.getDefaultRegistry();
                    }
                    linkedHashMap.put(str, parseAction(object2, defaultRegistry));
                }
                ArrayList arrayList = new ArrayList();
                List<Map<String, ?>> listOfObjects = JsonUtil.getListOfObjects(map, "route");
                if (listOfObjects != null && !listOfObjects.isEmpty()) {
                    Iterator<Map<String, ?>> it2 = listOfObjects.iterator();
                    while (it2.hasNext()) {
                        Route route = parseRoute(it2.next());
                        if (!linkedHashMap.containsKey(route.getActionName())) {
                            return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No action defined for route " + route + " in xds_routing LB policy: " + map));
                        }
                        arrayList.add(route);
                    }
                    return NameResolver.ConfigOrError.fromConfig(new XdsRoutingConfig(arrayList, linkedHashMap));
                }
                return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No routes provided for xds_routing LB policy: " + map));
            }
            return NameResolver.ConfigOrError.fromError(Status.INTERNAL.withDescription("No actions provided for xds_routing LB policy: " + map));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.fromThrowable(e).withDescription("Failed to parse xds_routing LB config: " + map));
        }
    }

    static final class XdsRoutingConfig {
        final Map<String, ServiceConfigUtil.PolicySelection> actions;
        final List<Route> routes;

        XdsRoutingConfig(List<Route> list, Map<String, ServiceConfigUtil.PolicySelection> map) {
            this.routes = ImmutableList.copyOf((Collection) list);
            this.actions = ImmutableMap.copyOf((Map) map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            XdsRoutingConfig xdsRoutingConfig = (XdsRoutingConfig) obj;
            return Objects.equals(this.routes, xdsRoutingConfig.routes) && Objects.equals(this.actions, xdsRoutingConfig.actions);
        }

        public int hashCode() {
            return Objects.hash(this.routes, this.actions);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("routes", this.routes).add("actions", this.actions).toString();
        }
    }

    static final class Route {
        private final String actionName;
        private final RouteMatch routeMatch;

        Route(RouteMatch routeMatch, String str) {
            this.routeMatch = routeMatch;
            this.actionName = str;
        }

        String getActionName() {
            return this.actionName;
        }

        RouteMatch getRouteMatch() {
            return this.routeMatch;
        }

        public int hashCode() {
            return Objects.hash(this.routeMatch, this.actionName);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Route route = (Route) obj;
            return Objects.equals(this.actionName, route.actionName) && Objects.equals(this.routeMatch, route.routeMatch);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("routeMatch", this.routeMatch).add("actionName", this.actionName).toString();
        }
    }
}
