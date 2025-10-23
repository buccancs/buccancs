package io.grpc.xds;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.LoadBalancer;
import io.grpc.Status;

/* loaded from: classes3.dex */
final class XdsSubchannelPickers {
    static final LoadBalancer.SubchannelPicker BUFFER_PICKER = new LoadBalancer.SubchannelPicker() { // from class: io.grpc.xds.XdsSubchannelPickers.1
        public String toString() {
            return "BUFFER_PICKER";
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }
    };

    private XdsSubchannelPickers() {
    }

    static final class ErrorPicker extends LoadBalancer.SubchannelPicker {
        private final Status error;

        ErrorPicker(Status status) {
            this.error = (Status) Preconditions.checkNotNull(status, "error");
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withError(this.error);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("error", this.error).toString();
        }
    }
}
