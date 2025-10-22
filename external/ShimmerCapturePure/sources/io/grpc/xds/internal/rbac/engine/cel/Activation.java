package io.grpc.xds.internal.rbac.engine.cel;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class Activation {
    public static Activation copyOf(Map<String, ?> map) {
        final ImmutableMap immutableMapCopyOf = ImmutableMap.copyOf((Map) map);
        return new Activation() { // from class: io.grpc.xds.internal.rbac.engine.cel.Activation.1
            @Override // io.grpc.xds.internal.rbac.engine.cel.Activation
            @Nullable
            public Object resolve(String str) {
                return immutableMapCopyOf.get(str);
            }

            public String toString() {
                return immutableMapCopyOf.toString();
            }
        };
    }

    @Nullable
    public abstract Object resolve(String str);
}
