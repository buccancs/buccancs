package io.grpc.netty.shaded.io.netty.util.internal.svm;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent0")
/* loaded from: classes3.dex */
final class PlatformDependent0Substitution {

    @RecomputeFieldValue(declClassName = "java.nio.Buffer", kind = RecomputeFieldValue.Kind.FieldOffset, name = "address")
    @Alias
    private static long ADDRESS_FIELD_OFFSET;

    private PlatformDependent0Substitution() {
    }
}
