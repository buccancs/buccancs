package io.opencensus.metrics;

import io.opencensus.metrics.export.ExportComponent;

/* loaded from: classes4.dex */
public abstract class MetricsComponent {
    static MetricsComponent newNoopMetricsComponent() {
        return new NoopMetricsComponent();
    }

    public abstract ExportComponent getExportComponent();

    public abstract MetricRegistry getMetricRegistry();

    private static final class NoopMetricsComponent extends MetricsComponent {
        private static final ExportComponent EXPORT_COMPONENT = ExportComponent.newNoopExportComponent();
        private static final MetricRegistry METRIC_REGISTRY = MetricRegistry.newNoopMetricRegistry();

        private NoopMetricsComponent() {
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public ExportComponent getExportComponent() {
            return EXPORT_COMPONENT;
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public MetricRegistry getMetricRegistry() {
            return METRIC_REGISTRY;
        }
    }
}
