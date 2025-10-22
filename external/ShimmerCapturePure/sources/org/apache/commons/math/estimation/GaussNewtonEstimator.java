package org.apache.commons.math.estimation;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
@Deprecated
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/estimation/GaussNewtonEstimator.class */
public class GaussNewtonEstimator extends AbstractEstimator implements Serializable {
    private static final long serialVersionUID = 5485001826076289109L;
    private static final double DEFAULT_STEADY_STATE_THRESHOLD = 1.0E-6d;
    private static final double DEFAULT_CONVERGENCE = 1.0E-6d;
    private double steadyStateThreshold;
    private double convergence;

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    public GaussNewtonEstimator(int r5) {
        /*
            r4 = this;
            r0 = r4
            super/*android.bluetooth.BluetoothAdapter*/.cancelDiscovery()
        r0 = r4
        r1 = 4517329193108106637 (0x3eb0c6f7a0b5ed8d,double:1.0E-6)
        r0.steadyStateThreshold = r1
        r0 = r4
        r1 = 4517329193108106637 (0x3eb0c6f7a0b5ed8d,double:1.0E-6)
        r0.convergence = r1
        return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.GaussNewtonEstimator.getIn(int):long");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r6v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r8v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0005: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('maxCostEval' int)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0010: MOVE (r1 I:??[long, double]) = (r6 I:??[long, double] A[D('convergence' double)]), block:B:2:0x0000 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x000a: MOVE (r1 I:??[long, double]) = (r8 I:??[long, double] A[D('steadyStateThreshold' double)]), block:B:2:0x0000 */
    public GaussNewtonEstimator() {
        /*
            r4 = this;
            r0 = r4
            super/*android.bluetooth.BluetoothAdapter*/.cancelDiscovery()
        r0 = r4
        r1 = r5
        r0.startDiscovery()
        r0 = r4
        r1 = r8
        r0.steadyStateThreshold = r1
        r0 = r4
        r1 = r6
        r0.convergence = r1
        return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.GaussNewtonEstimator.getInCount():int");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0001: MOVE (r1 I:??[long, double]) = (r5 I:??[long, double] A[D('convergence' double)]), block:B:2:0x0000 */
    public java.util.List getInList() {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r0.convergence = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.GaussNewtonEstimator.getInList():java.util.List");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0001: MOVE (r1 I:??[long, double]) = (r5 I:??[long, double] A[D('steadyStateThreshold' double)]), block:B:2:0x0000 */
    public long getLt() {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r0.steadyStateThreshold = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.GaussNewtonEstimator.getLt():long");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v46 ??, still in use, count: 1, list:
          (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) from 0x006c: IF  (r0v46 ?? I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) != (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:46:0x0114
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1119)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:110)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('problem' org.apache.commons.math.estimation.EstimationProblem)])
, block:B:2:0x0000 */
    public long getLte() throws org.apache.commons.math.estimation.EstimationException {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math.estimation.GaussNewtonEstimator.getLte():long");
    }
}
