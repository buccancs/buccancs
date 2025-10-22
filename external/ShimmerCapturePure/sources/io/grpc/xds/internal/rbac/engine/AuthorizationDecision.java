package io.grpc.xds.internal.rbac.engine;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public class AuthorizationDecision {
    private final Output decision;
    private final ImmutableList<String> policyNames;

    public AuthorizationDecision(Output output, List<String> list) {
        this.decision = output;
        this.policyNames = ImmutableList.copyOf((Collection) list);
    }

    public Output getDecision() {
        return this.decision;
    }

    public ImmutableList<String> getPolicyNames() {
        return this.policyNames;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = AnonymousClass1.$SwitchMap$io$grpc$xds$internal$rbac$engine$AuthorizationDecision$Output[this.decision.ordinal()];
        if (i == 1) {
            sb.append("Authorization Decision: ALLOW. \n");
        } else if (i == 2) {
            sb.append("Authorization Decision: DENY. \n");
        } else if (i == 3) {
            sb.append("Authorization Decision: UNKNOWN. \n");
        }
        UnmodifiableIterator<String> it2 = this.policyNames.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next() + "; \n");
        }
        return sb.toString();
    }

    public enum Output {
        ALLOW,
        DENY,
        UNKNOWN
    }

    /* renamed from: io.grpc.xds.internal.rbac.engine.AuthorizationDecision$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$xds$internal$rbac$engine$AuthorizationDecision$Output;

        static {
            int[] iArr = new int[Output.values().length];
            $SwitchMap$io$grpc$xds$internal$rbac$engine$AuthorizationDecision$Output = iArr;
            try {
                iArr[Output.ALLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$xds$internal$rbac$engine$AuthorizationDecision$Output[Output.DENY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$xds$internal$rbac$engine$AuthorizationDecision$Output[Output.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
