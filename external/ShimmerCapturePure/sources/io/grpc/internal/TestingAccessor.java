package io.grpc.internal;

/* loaded from: classes2.dex */
public final class TestingAccessor {
    private TestingAccessor() {
    }

    public static void setStatsEnabled(AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder, boolean z) {
        abstractManagedChannelImplBuilder.setStatsEnabled(z);
    }

    public static void setStatsEnabled(AbstractServerImplBuilder<?> abstractServerImplBuilder, boolean z) {
        abstractServerImplBuilder.setStatsEnabled(z);
    }
}
