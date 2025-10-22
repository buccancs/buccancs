package io.grpc.xds.shaded.io.envoyproxy.envoy.config.listener.v3;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntry;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntryOrBuilder;

/* loaded from: classes6.dex */
public interface ListenerCollectionOrBuilder extends MessageOrBuilder {
    CollectionEntry getEntries();

    CollectionEntryOrBuilder getEntriesOrBuilder();

    boolean hasEntries();
}
