package io.opencensus.trace;

import io.opencensus.common.Clock;
import io.opencensus.internal.ZeroTimeClock;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.export.ExportComponent;
import io.opencensus.trace.propagation.PropagationComponent;

/* loaded from: classes4.dex */
public abstract class TraceComponent {
    static TraceComponent newNoopTraceComponent() {
        return new NoopTraceComponent();
    }

    public abstract Clock getClock();

    public abstract ExportComponent getExportComponent();

    public abstract PropagationComponent getPropagationComponent();

    public abstract TraceConfig getTraceConfig();

    public abstract Tracer getTracer();

    private static final class NoopTraceComponent extends TraceComponent {
        private final ExportComponent noopExportComponent;

        private NoopTraceComponent() {
            this.noopExportComponent = ExportComponent.newNoopExportComponent();
        }

        @Override // io.opencensus.trace.TraceComponent
        public ExportComponent getExportComponent() {
            return this.noopExportComponent;
        }

        @Override // io.opencensus.trace.TraceComponent
        public Tracer getTracer() {
            return Tracer.getNoopTracer();
        }

        @Override // io.opencensus.trace.TraceComponent
        public PropagationComponent getPropagationComponent() {
            return PropagationComponent.getNoopPropagationComponent();
        }

        @Override // io.opencensus.trace.TraceComponent
        public Clock getClock() {
            return ZeroTimeClock.getInstance();
        }

        @Override // io.opencensus.trace.TraceComponent
        public TraceConfig getTraceConfig() {
            return TraceConfig.getNoopTraceConfig();
        }
    }
}
