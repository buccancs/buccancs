package io.grpc;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.LoadBalancer;

import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public abstract class InternalConfigSelector {
    public static final Attributes.Key<InternalConfigSelector> KEY = Attributes.Key.create("io.grpc.config-selector");

    public abstract Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs);

    public static final class Result {

        @Nullable
        private final CallOptions callOptions;

        @Nullable
        private final Runnable committedCallback;

        @Nullable
        private final Object config;
        private final Status status;

        private Result(Status status, Object obj, CallOptions callOptions, Runnable runnable) {
            this.status = (Status) Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
            this.config = obj;
            this.callOptions = callOptions;
            this.committedCallback = runnable;
        }

        public static Result forError(Status status) {
            Preconditions.checkArgument(!status.isOk(), "status is OK");
            return new Result(status, null, null, null);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        @Nullable
        public CallOptions getCallOptions() {
            return this.callOptions;
        }

        @Nullable
        public Runnable getCommittedCallback() {
            return this.committedCallback;
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        public Status getStatus() {
            return this.status;
        }

        public static final class Builder {
            private CallOptions callOptions;
            private Runnable committedCallback;
            private Object config;

            private Builder() {
            }

            public Builder setCommittedCallback(@Nullable Runnable runnable) {
                this.committedCallback = runnable;
                return this;
            }

            public Builder setConfig(Object obj) {
                this.config = Preconditions.checkNotNull(obj, "config");
                return this;
            }

            public Builder setCallOptions(CallOptions callOptions) {
                this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
                return this;
            }

            public Result build() {
                Preconditions.checkState(this.config != null, "config is not set");
                Preconditions.checkState(this.callOptions != null, "callOptions is not set");
                return new Result(Status.OK, this.config, this.callOptions, this.committedCallback);
            }
        }
    }
}
