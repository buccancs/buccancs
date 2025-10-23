package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Decl;

/* loaded from: classes3.dex */
public interface DeclOrBuilder extends MessageOrBuilder {
    Decl.DeclKindCase getDeclKindCase();

    Decl.FunctionDecl getFunction();

    Decl.FunctionDeclOrBuilder getFunctionOrBuilder();

    Decl.IdentDecl getIdent();

    Decl.IdentDeclOrBuilder getIdentOrBuilder();

    String getName();

    ByteString getNameBytes();

    boolean hasFunction();

    boolean hasIdent();
}
