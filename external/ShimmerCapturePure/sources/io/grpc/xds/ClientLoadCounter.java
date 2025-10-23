package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ClientStreamTracer;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.util.ForwardingClientStreamTracer;
import io.grpc.xds.OrcaOobUtil;
import io.grpc.xds.OrcaPerRequestUtil;
import io.grpc.xds.shaded.com.github.udpa.udpa.data.orca.v1.OrcaLoadReport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes3.dex */
final class ClientLoadCounter {
    private static final int THREAD_BALANCING_FACTOR = 64;
    private final AtomicLong callsInProgress = new AtomicLong();
    private final AtomicLong callsSucceeded = new AtomicLong();
    private final AtomicLong callsFailed = new AtomicLong();
    private final AtomicLong callsIssued = new AtomicLong();
    private final MetricRecorder[] metricRecorders = new MetricRecorder[64];

    ClientLoadCounter() {
        for (int i = 0; i < 64; i++) {
            this.metricRecorders[i] = new MetricRecorder();
        }
    }

    void recordCallStarted() {
        this.callsIssued.getAndIncrement();
        this.callsInProgress.getAndIncrement();
    }

    void recordCallFinished(Status status) {
        this.callsInProgress.getAndDecrement();
        if (status.isOk()) {
            this.callsSucceeded.getAndIncrement();
        } else {
            this.callsFailed.getAndIncrement();
        }
    }

    void recordMetric(String str, double d) {
        this.metricRecorders[(int) (Thread.currentThread().getId() % 64)].addValue(str, d);
    }

    ClientLoadSnapshot snapshot() {
        HashMap map = new HashMap();
        for (MetricRecorder metricRecorder : this.metricRecorders) {
            for (Map.Entry<String, MetricValue> entry : metricRecorder.takeAll().entrySet()) {
                MetricValue metricValue = (MetricValue) map.get(entry.getKey());
                if (metricValue == null) {
                    metricValue = new MetricValue();
                    map.put(entry.getKey(), metricValue);
                }
                MetricValue value = entry.getValue();
                MetricValue.access$212(metricValue, value.numReports);
                MetricValue.access$318(metricValue, value.totalValue);
            }
        }
        return new ClientLoadSnapshot(this.callsSucceeded.getAndSet(0L), this.callsInProgress.get(), this.callsFailed.getAndSet(0L), this.callsIssued.getAndSet(0L), map);
    }

    void setCallsIssued(long j) {
        this.callsIssued.set(j);
    }

    void setCallsInProgress(long j) {
        this.callsInProgress.set(j);
    }

    void setCallsSucceeded(long j) {
        this.callsSucceeded.set(j);
    }

    void setCallsFailed(long j) {
        this.callsFailed.set(j);
    }

    static final class ClientLoadSnapshot {
        static final ClientLoadSnapshot EMPTY_SNAPSHOT = new ClientLoadSnapshot(0, 0, 0, 0, Collections.EMPTY_MAP);
        private final long callsFailed;
        private final long callsInProgress;
        private final long callsIssued;
        private final long callsSucceeded;
        private final Map<String, MetricValue> metricValues;

        ClientLoadSnapshot(long j, long j2, long j3, long j4, Map<String, MetricValue> map) {
            this.callsSucceeded = j;
            this.callsInProgress = j2;
            this.callsFailed = j3;
            this.callsIssued = j4;
            this.metricValues = (Map) Preconditions.checkNotNull(map, "metricValues");
        }

        long getCallsFailed() {
            return this.callsFailed;
        }

        long getCallsInProgress() {
            return this.callsInProgress;
        }

        long getCallsIssued() {
            return this.callsIssued;
        }

        long getCallsSucceeded() {
            return this.callsSucceeded;
        }

        Map<String, MetricValue> getMetricValues() {
            return Collections.unmodifiableMap(this.metricValues);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("callsSucceeded", this.callsSucceeded).add("callsInProgress", this.callsInProgress).add("callsFailed", this.callsFailed).add("callsIssued", this.callsIssued).add("metricValues", this.metricValues).toString();
        }
    }

    static final class MetricValue {
        private int numReports;
        private double totalValue;

        private MetricValue() {
            this(0, 0.0d);
        }

        MetricValue(int i, double d) {
            this.numReports = i;
            this.totalValue = d;
        }

        static /* synthetic */ int access$208(MetricValue metricValue) {
            int i = metricValue.numReports;
            metricValue.numReports = i + 1;
            return i;
        }

        static /* synthetic */ int access$212(MetricValue metricValue, int i) {
            int i2 = metricValue.numReports + i;
            metricValue.numReports = i2;
            return i2;
        }

        static /* synthetic */ double access$318(MetricValue metricValue, double d) {
            double d2 = metricValue.totalValue + d;
            metricValue.totalValue = d2;
            return d2;
        }

        long getNumReports() {
            return this.numReports;
        }

        double getTotalValue() {
            return this.totalValue;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("numReports", this.numReports).add("totalValue", this.totalValue).toString();
        }
    }

    private static class MetricRecorder {
        private Map<String, MetricValue> metricValues;

        private MetricRecorder() {
            this.metricValues = new HashMap();
        }

        synchronized void addValue(String str, double d) {
            MetricValue metricValue = this.metricValues.get(str);
            if (metricValue == null) {
                metricValue = new MetricValue();
            }
            MetricValue.access$208(metricValue);
            MetricValue.access$318(metricValue, d);
            this.metricValues.put(str, metricValue);
        }

        synchronized Map<String, MetricValue> takeAll() {
            Map<String, MetricValue> map;
            map = this.metricValues;
            this.metricValues = new HashMap();
            return map;
        }
    }

    static final class LoadRecordingStreamTracerFactory extends ClientStreamTracer.Factory {
        private final ClientLoadCounter counter;
        private final ClientStreamTracer.Factory delegate;

        LoadRecordingStreamTracerFactory(ClientLoadCounter clientLoadCounter, ClientStreamTracer.Factory factory) {
            this.counter = (ClientLoadCounter) Preconditions.checkNotNull(clientLoadCounter, "counter");
            this.delegate = (ClientStreamTracer.Factory) Preconditions.checkNotNull(factory, "delegate");
        }

        ClientStreamTracer.Factory delegate() {
            return this.delegate;
        }

        ClientLoadCounter getCounter() {
            return this.counter;
        }

        @Override // io.grpc.ClientStreamTracer.Factory
        public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
            this.counter.recordCallStarted();
            final ClientStreamTracer clientStreamTracerNewClientStreamTracer = this.delegate.newClientStreamTracer(streamInfo, metadata);
            return new ForwardingClientStreamTracer() { // from class: io.grpc.xds.ClientLoadCounter.LoadRecordingStreamTracerFactory.1
                @Override // io.grpc.util.ForwardingClientStreamTracer
                protected ClientStreamTracer delegate() {
                    return clientStreamTracerNewClientStreamTracer;
                }

                @Override // io.grpc.util.ForwardingClientStreamTracer, io.grpc.StreamTracer
                public void streamClosed(Status status) {
                    LoadRecordingStreamTracerFactory.this.counter.recordCallFinished(status);
                    delegate().streamClosed(status);
                }
            };
        }
    }

    static final class MetricsRecordingListener implements OrcaPerRequestUtil.OrcaPerRequestReportListener, OrcaOobUtil.OrcaOobReportListener {
        private final ClientLoadCounter counter;

        MetricsRecordingListener(ClientLoadCounter clientLoadCounter) {
            this.counter = (ClientLoadCounter) Preconditions.checkNotNull(clientLoadCounter, "counter");
        }

        ClientLoadCounter getCounter() {
            return this.counter;
        }

        @Override
        // io.grpc.xds.OrcaPerRequestUtil.OrcaPerRequestReportListener, io.grpc.xds.OrcaOobUtil.OrcaOobReportListener
        public void onLoadReport(OrcaLoadReport orcaLoadReport) {
            this.counter.recordMetric("cpu_utilization", orcaLoadReport.getCpuUtilization());
            this.counter.recordMetric("mem_utilization", orcaLoadReport.getMemUtilization());
            for (Map.Entry<String, Double> entry : orcaLoadReport.getRequestCostMap().entrySet()) {
                this.counter.recordMetric(entry.getKey(), entry.getValue().doubleValue());
            }
            for (Map.Entry<String, Double> entry2 : orcaLoadReport.getUtilizationMap().entrySet()) {
                this.counter.recordMetric(entry2.getKey(), entry2.getValue().doubleValue());
            }
        }
    }

    static abstract class TracerWrappingSubchannelPicker extends LoadBalancer.SubchannelPicker {
        private static final ClientStreamTracer NOOP_CLIENT_STREAM_TRACER = new ClientStreamTracer() { // from class: io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker.1
        };
        private static final ClientStreamTracer.Factory NOOP_CLIENT_STREAM_TRACER_FACTORY = new ClientStreamTracer.Factory() { // from class: io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker.2
            @Override // io.grpc.ClientStreamTracer.Factory
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return TracerWrappingSubchannelPicker.NOOP_CLIENT_STREAM_TRACER;
            }
        };

        TracerWrappingSubchannelPicker() {
        }

        protected abstract LoadBalancer.SubchannelPicker delegate();

        protected abstract ClientStreamTracer.Factory wrapTracerFactory(ClientStreamTracer.Factory factory);

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.PickResult pickResultPickSubchannel = delegate().pickSubchannel(pickSubchannelArgs);
            if (!pickResultPickSubchannel.getStatus().isOk() || pickResultPickSubchannel.getSubchannel() == null) {
                return pickResultPickSubchannel;
            }
            ClientStreamTracer.Factory streamTracerFactory = pickResultPickSubchannel.getStreamTracerFactory();
            if (streamTracerFactory == null) {
                streamTracerFactory = NOOP_CLIENT_STREAM_TRACER_FACTORY;
            }
            return LoadBalancer.PickResult.withSubchannel(pickResultPickSubchannel.getSubchannel(), wrapTracerFactory(streamTracerFactory));
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
        }
    }

    static final class LoadRecordingSubchannelPicker extends TracerWrappingSubchannelPicker {
        private final ClientLoadCounter counter;
        private final LoadBalancer.SubchannelPicker delegate;

        LoadRecordingSubchannelPicker(ClientLoadCounter clientLoadCounter, LoadBalancer.SubchannelPicker subchannelPicker) {
            this.counter = (ClientLoadCounter) Preconditions.checkNotNull(clientLoadCounter, "counter");
            this.delegate = (LoadBalancer.SubchannelPicker) Preconditions.checkNotNull(subchannelPicker, "delegate");
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        protected LoadBalancer.SubchannelPicker delegate() {
            return this.delegate;
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        protected ClientStreamTracer.Factory wrapTracerFactory(ClientStreamTracer.Factory factory) {
            return new LoadRecordingStreamTracerFactory(this.counter, factory);
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) LoadRecordingSubchannelPicker.class).add("delegate", this.delegate).toString();
        }
    }

    static final class MetricsObservingSubchannelPicker extends TracerWrappingSubchannelPicker {
        private final LoadBalancer.SubchannelPicker delegate;
        private final OrcaPerRequestUtil.OrcaPerRequestReportListener listener;
        private final OrcaPerRequestUtil orcaPerRequestUtil;

        MetricsObservingSubchannelPicker(OrcaPerRequestUtil.OrcaPerRequestReportListener orcaPerRequestReportListener, LoadBalancer.SubchannelPicker subchannelPicker, OrcaPerRequestUtil orcaPerRequestUtil) {
            this.listener = (OrcaPerRequestUtil.OrcaPerRequestReportListener) Preconditions.checkNotNull(orcaPerRequestReportListener, "listener");
            this.delegate = (LoadBalancer.SubchannelPicker) Preconditions.checkNotNull(subchannelPicker, "delegate");
            this.orcaPerRequestUtil = (OrcaPerRequestUtil) Preconditions.checkNotNull(orcaPerRequestUtil, "orcaPerRequestUtil");
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        protected LoadBalancer.SubchannelPicker delegate() {
            return this.delegate;
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        protected ClientStreamTracer.Factory wrapTracerFactory(ClientStreamTracer.Factory factory) {
            return this.orcaPerRequestUtil.newOrcaClientStreamTracerFactory(factory, this.listener);
        }

        @Override // io.grpc.xds.ClientLoadCounter.TracerWrappingSubchannelPicker
        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) MetricsObservingSubchannelPicker.class).add("delegate", this.delegate).toString();
        }
    }
}
