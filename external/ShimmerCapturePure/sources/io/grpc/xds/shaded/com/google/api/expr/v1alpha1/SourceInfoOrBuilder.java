package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface SourceInfoOrBuilder extends MessageOrBuilder {
    boolean containsMacroCalls(long j);

    boolean containsPositions(long j);

    int getLineOffsets(int i);

    int getLineOffsetsCount();

    List<Integer> getLineOffsetsList();

    String getLocation();

    ByteString getLocationBytes();

    @Deprecated
    Map<Long, Expr> getMacroCalls();

    int getMacroCallsCount();

    Map<Long, Expr> getMacroCallsMap();

    Expr getMacroCallsOrDefault(long j, Expr expr);

    Expr getMacroCallsOrThrow(long j);

    @Deprecated
    Map<Long, Integer> getPositions();

    int getPositionsCount();

    Map<Long, Integer> getPositionsMap();

    int getPositionsOrDefault(long j, int i);

    int getPositionsOrThrow(long j);

    String getSyntaxVersion();

    ByteString getSyntaxVersionBytes();
}
