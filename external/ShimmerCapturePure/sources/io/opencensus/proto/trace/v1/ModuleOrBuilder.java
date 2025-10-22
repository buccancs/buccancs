package io.opencensus.proto.trace.v1;

import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes4.dex */
public interface ModuleOrBuilder extends MessageOrBuilder {
    TruncatableString getBuildId();

    TruncatableStringOrBuilder getBuildIdOrBuilder();

    TruncatableString getModule();

    TruncatableStringOrBuilder getModuleOrBuilder();

    boolean hasBuildId();

    boolean hasModule();
}
