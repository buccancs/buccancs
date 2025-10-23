package io.grpc.xds.shaded.com.google.security.meshca.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;

/* loaded from: classes3.dex */
public interface MeshCertificateResponseOrBuilder extends MessageOrBuilder {
    String getCertChain(int i);

    ByteString getCertChainBytes(int i);

    int getCertChainCount();

    /* renamed from: getCertChainList */
    List<String> mo11557getCertChainList();
}
