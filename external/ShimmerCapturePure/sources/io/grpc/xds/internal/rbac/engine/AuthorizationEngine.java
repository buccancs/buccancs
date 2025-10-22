package io.grpc.xds.internal.rbac.engine;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.xds.internal.rbac.engine.AuthorizationDecision;
import io.grpc.xds.internal.rbac.engine.cel.Activation;
import io.grpc.xds.internal.rbac.engine.cel.DefaultDispatcher;
import io.grpc.xds.internal.rbac.engine.cel.DefaultInterpreter;
import io.grpc.xds.internal.rbac.engine.cel.DescriptorMessageProvider;
import io.grpc.xds.internal.rbac.engine.cel.IncompleteData;
import io.grpc.xds.internal.rbac.engine.cel.InterpreterException;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Expr;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.Policy;
import io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public class AuthorizationEngine {
    private static final Logger log = Logger.getLogger(AuthorizationEngine.class.getName());
    private final RbacEngine allowEngine;
    private final RbacEngine denyEngine;

    public AuthorizationEngine(RBAC rbac) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Policy> entry : rbac.getPolicies().entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().getCondition());
        }
        this.allowEngine = rbac.getAction() == RBAC.Action.ALLOW ? new RbacEngine(RBAC.Action.ALLOW, ImmutableMap.copyOf((Map) linkedHashMap)) : null;
        this.denyEngine = rbac.getAction() == RBAC.Action.DENY ? new RbacEngine(RBAC.Action.DENY, ImmutableMap.copyOf((Map) linkedHashMap)) : null;
    }

    public AuthorizationEngine(RBAC rbac, RBAC rbac2) throws IllegalArgumentException {
        Preconditions.checkArgument(rbac.getAction() == RBAC.Action.DENY && rbac2.getAction() == RBAC.Action.ALLOW, "Invalid RBAC list, must provide a RBAC with DENY action followed by a RBAC with ALLOW action. ");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Policy> entry : rbac.getPolicies().entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().getCondition());
        }
        this.denyEngine = new RbacEngine(RBAC.Action.DENY, ImmutableMap.copyOf((Map) linkedHashMap));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, Policy> entry2 : rbac2.getPolicies().entrySet()) {
            linkedHashMap2.put(entry2.getKey(), entry2.getValue().getCondition());
        }
        this.allowEngine = new RbacEngine(RBAC.Action.ALLOW, ImmutableMap.copyOf((Map) linkedHashMap2));
    }

    public AuthorizationDecision evaluate(EvaluateArgs evaluateArgs) {
        ArrayList arrayList = new ArrayList();
        Activation activationCopyOf = Activation.copyOf(evaluateArgs.generateEnvoyAttributes());
        RbacEngine rbacEngine = this.denyEngine;
        if (rbacEngine != null) {
            AuthorizationDecision authorizationDecisionEvaluateEngine = evaluateEngine(rbacEngine.conditions.entrySet(), AuthorizationDecision.Output.DENY, arrayList, activationCopyOf);
            if (authorizationDecisionEvaluateEngine != null) {
                return authorizationDecisionEvaluateEngine;
            }
            if (arrayList.size() > 0) {
                return new AuthorizationDecision(AuthorizationDecision.Output.UNKNOWN, arrayList);
            }
        }
        RbacEngine rbacEngine2 = this.allowEngine;
        if (rbacEngine2 != null) {
            AuthorizationDecision authorizationDecisionEvaluateEngine2 = evaluateEngine(rbacEngine2.conditions.entrySet(), AuthorizationDecision.Output.ALLOW, arrayList, activationCopyOf);
            if (authorizationDecisionEvaluateEngine2 != null) {
                return authorizationDecisionEvaluateEngine2;
            }
            if (arrayList.size() > 0) {
                return new AuthorizationDecision(AuthorizationDecision.Output.UNKNOWN, arrayList);
            }
        }
        if (this.allowEngine == null && this.denyEngine != null) {
            return new AuthorizationDecision(AuthorizationDecision.Output.ALLOW, new ArrayList());
        }
        return new AuthorizationDecision(AuthorizationDecision.Output.DENY, new ArrayList());
    }

    protected AuthorizationDecision evaluateEngine(Set<Map.Entry<String, Expr>> set, AuthorizationDecision.Output output, List<String> list, Activation activation) {
        for (Map.Entry<String, Expr> entry : set) {
            try {
            } catch (InterpreterException unused) {
                list.add(entry.getKey());
            }
            if (matches(entry.getValue(), activation)) {
                return new AuthorizationDecision(output, new ArrayList(Arrays.asList(entry.getKey())));
            }
            continue;
        }
        return null;
    }

    protected boolean matches(Expr expr, Activation activation) throws InterpreterException {
        try {
            Object objEval = new DefaultInterpreter(DescriptorMessageProvider.dynamicMessages(new ArrayList()), DefaultDispatcher.create()).createInterpretable(expr).eval(activation);
            if (objEval instanceof Boolean) {
                return Boolean.valueOf(objEval.toString()).booleanValue();
            }
            if (objEval instanceof IncompleteData) {
                throw new InterpreterException.Builder("Envoy Attributes gotten are incomplete.", new Object[0]).build();
            }
            return false;
        } catch (InterpreterException e) {
            log.log(Level.WARNING, e.toString(), (Throwable) e);
            throw e;
        }
    }

    private static class RbacEngine {
        private final RBAC.Action action;
        private final ImmutableMap<String, Expr> conditions;

        public RbacEngine(RBAC.Action action, ImmutableMap<String, Expr> immutableMap) {
            this.action = (RBAC.Action) Preconditions.checkNotNull(action);
            this.conditions = (ImmutableMap) Preconditions.checkNotNull(immutableMap);
        }
    }
}
