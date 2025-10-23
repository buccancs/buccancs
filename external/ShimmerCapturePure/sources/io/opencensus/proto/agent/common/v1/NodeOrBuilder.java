package io.opencensus.proto.agent.common.v1;

import com.google.protobuf.MessageOrBuilder;

import java.util.Map;

/* loaded from: classes4.dex */
public interface NodeOrBuilder extends MessageOrBuilder {
    boolean containsAttributes(String str);

    @Deprecated
    Map<String, String> getAttributes();

    int getAttributesCount();

    Map<String, String> getAttributesMap();

    String getAttributesOrDefault(String str, String str2);

    String getAttributesOrThrow(String str);

    ProcessIdentifier getIdentifier();

    ProcessIdentifierOrBuilder getIdentifierOrBuilder();

    LibraryInfo getLibraryInfo();

    LibraryInfoOrBuilder getLibraryInfoOrBuilder();

    ServiceInfo getServiceInfo();

    ServiceInfoOrBuilder getServiceInfoOrBuilder();

    boolean hasIdentifier();

    boolean hasLibraryInfo();

    boolean hasServiceInfo();
}
