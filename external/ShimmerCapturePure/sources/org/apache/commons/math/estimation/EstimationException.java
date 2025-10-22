package org.apache.commons.math.estimation;

import io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator;

import java.nio.file.attribute.PosixFilePermission;

import org.apache.commons.math.MathException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/estimation/EstimationException.class */
public class EstimationException extends MathException {
    private static final long serialVersionUID = -573038581493881337L;

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v0 ??, still in use, count: 2, list:
          (r1v0 ?? I:androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0) from 0x0006: INVOKE (r0v0 'unused' java.nio.file.Path A[SYNTHETIC]) = 
          (r1v0 ?? I:androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0)
          (r6v0 com.google.protobuf.ByteString A[D('specifier' java.lang.String)])
         STATIC call: java.nio.file.Paths.get(java.lang.String, java.lang.String[]):java.nio.file.Path A[MD:(java.lang.String, java.lang.String[]):java.nio.file.Path VARARG (c), VARARG_CALL] (LINE:47)
          (r1v0 ?? I:java.nio.file.Path) from 0x000a: INVOKE (r0v1 'unused' java.nio.file.Path A[SYNTHETIC]) = 
          (r5v0 'this' ?? I:androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0 A[D('this' org.apache.commons.math.estimation.EstimationException), IMMUTABLE_TYPE, THIS])
          (r1v0 ?? I:java.nio.file.Path)
         STATIC call: java.nio.file.Files.setPosixFilePermissions(java.nio.file.Path, java.util.Set):java.nio.file.Path A[MD:(java.nio.file.Path, java.util.Set<java.nio.file.attribute.PosixFilePermission>):java.nio.file.Path throws java.io.IOException (c)] (LINE:47)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    /* JADX WARN: Failed to calculate best type for var: r7v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r7v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0009: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('parts' java.lang.Object[])]), block:B:2:0x0000 */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0, java.nio.file.Path, org.apache.commons.math.exception.util.DummyLocalizable] */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0, java.lang.String[], org.apache.commons.math.estimation.EstimationException] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.Set] */
    public EstimationException(com.google.protobuf.ByteString r6) {
        /*
            r5 = this;
            r0 = r5
            org.apache.commons.math.exception.util.DummyLocalizable r1 = new org.apache.commons.math.exception.util.DummyLocalizable
            r2 = r1
            r3 = r6
            super/*androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0*/.m(r3, r0)
        r2 = r7
        super/*androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0*/.m(r1, r2)
        return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.EstimationException.setAuthorityBytes(com.google.protobuf.ByteString):io.grpc.xds.shaded.com.github.udpa.udpa.core.v1.ResourceLocator$Builder");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EstimationException(int i, ResourceLocator.Directive.Builder builder) {
        PosixFilePermission unused = PosixFilePermission.OWNER_EXECUTE;
        return;
    }
}
