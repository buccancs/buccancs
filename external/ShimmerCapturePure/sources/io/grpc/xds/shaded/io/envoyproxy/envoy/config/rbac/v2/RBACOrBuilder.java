package io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC;

import java.util.Map;

/* loaded from: classes4.dex */
public interface RBACOrBuilder extends MessageOrBuilder {
    boolean containsPolicies(String str);

    RBAC.Action getAction();

    int getActionValue();

    @Deprecated
    Map<String, Policy> getPolicies();

    int getPoliciesCount();

    Map<String, Policy> getPoliciesMap();

    Policy getPoliciesOrDefault(String str, Policy policy);

    Policy getPoliciesOrThrow(String str);
}
