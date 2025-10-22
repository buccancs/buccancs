package io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.ExprOrBuilder;

import java.util.List;

/* loaded from: classes6.dex */
public interface PolicyOrBuilder extends MessageOrBuilder {
    Expr getCondition();

    ExprOrBuilder getConditionOrBuilder();

    Permission getPermissions(int i);

    int getPermissionsCount();

    List<Permission> getPermissionsList();

    PermissionOrBuilder getPermissionsOrBuilder(int i);

    List<? extends PermissionOrBuilder> getPermissionsOrBuilderList();

    Principal getPrincipals(int i);

    int getPrincipalsCount();

    List<Principal> getPrincipalsList();

    PrincipalOrBuilder getPrincipalsOrBuilder(int i);

    List<? extends PrincipalOrBuilder> getPrincipalsOrBuilderList();

    boolean hasCondition();
}
