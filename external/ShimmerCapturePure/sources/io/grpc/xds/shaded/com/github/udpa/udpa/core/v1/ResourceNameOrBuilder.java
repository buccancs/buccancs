package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface ResourceNameOrBuilder extends MessageOrBuilder {
    String getAuthority();

    ByteString getAuthorityBytes();

    ContextParams getContext();

    ContextParamsOrBuilder getContextOrBuilder();

    String getId(int i);

    ByteString getIdBytes(int i);

    int getIdCount();

    /* renamed from: getIdList */
    List<String> mo10311getIdList();

    String getResourceType();

    ByteString getResourceTypeBytes();

    boolean hasContext();
}
