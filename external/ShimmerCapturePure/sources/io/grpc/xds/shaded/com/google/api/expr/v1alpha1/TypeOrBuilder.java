package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.protobuf.EmptyOrBuilder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.NullValue;
import io.grpc.xds.shaded.com.google.api.expr.v1alpha1.Type;

/* loaded from: classes3.dex */
public interface TypeOrBuilder extends MessageOrBuilder {
    Type.AbstractType getAbstractType();

    Type.AbstractTypeOrBuilder getAbstractTypeOrBuilder();

    Empty getDyn();

    EmptyOrBuilder getDynOrBuilder();

    Empty getError();

    EmptyOrBuilder getErrorOrBuilder();

    Type.FunctionType getFunction();

    Type.FunctionTypeOrBuilder getFunctionOrBuilder();

    Type.ListType getListType();

    Type.ListTypeOrBuilder getListTypeOrBuilder();

    Type.MapType getMapType();

    Type.MapTypeOrBuilder getMapTypeOrBuilder();

    String getMessageType();

    ByteString getMessageTypeBytes();

    NullValue getNull();

    int getNullValue();

    Type.PrimitiveType getPrimitive();

    int getPrimitiveValue();

    Type getType();

    Type.TypeKindCase getTypeKindCase();

    TypeOrBuilder getTypeOrBuilder();

    String getTypeParam();

    ByteString getTypeParamBytes();

    Type.WellKnownType getWellKnown();

    int getWellKnownValue();

    Type.PrimitiveType getWrapper();

    int getWrapperValue();

    boolean hasAbstractType();

    boolean hasDyn();

    boolean hasError();

    boolean hasFunction();

    boolean hasListType();

    boolean hasMapType();

    boolean hasType();
}
