package io.grpc.xds.shaded.com.github.udpa.udpa.core.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;

import java.util.List;

/* loaded from: classes3.dex */
public interface ResourceLocatorOrBuilder extends MessageOrBuilder {
    String getAuthority();

    ByteString getAuthorityBytes();

    ResourceLocator.ContextParamSpecifierCase getContextParamSpecifierCase();

    ResourceLocator.Directive getDirectives(int i);

    int getDirectivesCount();

    List<ResourceLocator.Directive> getDirectivesList();

    ResourceLocator.DirectiveOrBuilder getDirectivesOrBuilder(int i);

    List<? extends ResourceLocator.DirectiveOrBuilder> getDirectivesOrBuilderList();

    ContextParams getExactContext();

    ContextParamsOrBuilder getExactContextOrBuilder();

    String getId(int i);

    ByteString getIdBytes(int i);

    int getIdCount();

    /* renamed from: getIdList */
    List<String> mo10217getIdList();

    String getResourceType();

    ByteString getResourceTypeBytes();

    ResourceLocator.Scheme getScheme();

    int getSchemeValue();

    boolean hasExactContext();
}
