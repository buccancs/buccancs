package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.CollectionEntry;

/* loaded from: classes3.dex */
public interface CollectionEntryOrBuilder extends MessageOrBuilder {
    CollectionEntry.InlineEntry getInlineEntry();

    CollectionEntry.InlineEntryOrBuilder getInlineEntryOrBuilder();

    ResourceLocator getLocator();

    ResourceLocatorOrBuilder getLocatorOrBuilder();

    CollectionEntry.ResourceSpecifierCase getResourceSpecifierCase();

    boolean hasInlineEntry();

    boolean hasLocator();
}
