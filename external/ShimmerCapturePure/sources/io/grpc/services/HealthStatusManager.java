package io.grpc.services;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.BindableService;
import io.grpc.health.v1.HealthCheckResponse;

/* loaded from: classes3.dex */
public final class HealthStatusManager {
    public static final String SERVICE_NAME_ALL_SERVICES = "";
    private final HealthServiceImpl healthService = new HealthServiceImpl();

    public BindableService getHealthService() {
        return this.healthService;
    }

    public void setStatus(String str, HealthCheckResponse.ServingStatus servingStatus) {
        Preconditions.checkNotNull(servingStatus, NotificationCompat.CATEGORY_STATUS);
        this.healthService.setStatus(str, servingStatus);
    }

    public void clearStatus(String str) {
        this.healthService.clearStatus(str);
    }

    public void enterTerminalState() {
        this.healthService.enterTerminalState();
    }
}
