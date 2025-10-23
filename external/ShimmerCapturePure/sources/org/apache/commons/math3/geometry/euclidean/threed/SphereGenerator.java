package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes5.dex */
public class SphereGenerator implements SupportBallGenerator<Euclidean3D, Vector3D> {
    @Override // org.apache.commons.math3.geometry.enclosing.SupportBallGenerator
    public EnclosingBall<Euclidean3D, Vector3D> ballOnSupport(List<Vector3D> list) {
        if (list.size() < 1) {
            return new EnclosingBall<>(Vector3D.ZERO, Double.NEGATIVE_INFINITY, new Vector3D[0]);
        }
        Vector3D vector3D = list.get(0);
        if (list.size() < 2) {
            return new EnclosingBall<>(vector3D, 0.0d, vector3D);
        }
        Vector3D vector3D2 = list.get(1);
        if (list.size() < 3) {
            return new EnclosingBall<>(new Vector3D(0.5d, vector3D, 0.5d, vector3D2), vector3D.distance((Vector<Euclidean3D>) vector3D2) * 0.5d, vector3D, vector3D2);
        }
        Vector3D vector3D3 = list.get(2);
        if (list.size() < 4) {
            Plane plane = new Plane(vector3D, vector3D2, vector3D3, (vector3D.getNorm1() + vector3D2.getNorm1() + vector3D3.getNorm1()) * 1.0E-10d);
            EnclosingBall<Euclidean2D, Vector2D> enclosingBallBallOnSupport = new DiskGenerator().ballOnSupport(Arrays.asList(plane.toSubSpace((Vector<Euclidean3D>) vector3D), plane.toSubSpace((Vector<Euclidean3D>) vector3D2), plane.toSubSpace((Vector<Euclidean3D>) vector3D3)));
            return new EnclosingBall<>(plane.toSpace((Vector<Euclidean2D>) enclosingBallBallOnSupport.getCenter()), enclosingBallBallOnSupport.getRadius(), vector3D, vector3D2, vector3D3);
        }
        Vector3D vector3D4 = list.get(3);
        BigFraction[] bigFractionArr = {new BigFraction(vector3D.getX()), new BigFraction(vector3D2.getX()), new BigFraction(vector3D3.getX()), new BigFraction(vector3D4.getX())};
        BigFraction[] bigFractionArr2 = {new BigFraction(vector3D.getY()), new BigFraction(vector3D2.getY()), new BigFraction(vector3D3.getY()), new BigFraction(vector3D4.getY())};
        BigFraction[] bigFractionArr3 = {new BigFraction(vector3D.getZ()), new BigFraction(vector3D2.getZ()), new BigFraction(vector3D3.getZ()), new BigFraction(vector3D4.getZ())};
        BigFraction bigFraction = bigFractionArr[0];
        BigFraction bigFractionMultiply = bigFraction.multiply(bigFraction);
        BigFraction bigFraction2 = bigFractionArr2[0];
        BigFraction bigFractionAdd = bigFractionMultiply.add(bigFraction2.multiply(bigFraction2));
        BigFraction bigFraction3 = bigFractionArr3[0];
        BigFraction bigFraction4 = bigFractionArr[1];
        BigFraction bigFractionMultiply2 = bigFraction4.multiply(bigFraction4);
        BigFraction bigFraction5 = bigFractionArr2[1];
        BigFraction bigFractionAdd2 = bigFractionMultiply2.add(bigFraction5.multiply(bigFraction5));
        BigFraction bigFraction6 = bigFractionArr3[1];
        BigFraction bigFraction7 = bigFractionArr[2];
        BigFraction bigFractionMultiply3 = bigFraction7.multiply(bigFraction7);
        BigFraction bigFraction8 = bigFractionArr2[2];
        BigFraction bigFractionAdd3 = bigFractionMultiply3.add(bigFraction8.multiply(bigFraction8));
        BigFraction bigFraction9 = bigFractionArr3[2];
        BigFraction bigFraction10 = bigFractionArr[3];
        BigFraction bigFractionMultiply4 = bigFraction10.multiply(bigFraction10);
        BigFraction bigFraction11 = bigFractionArr2[3];
        BigFraction bigFractionAdd4 = bigFractionMultiply4.add(bigFraction11.multiply(bigFraction11));
        BigFraction bigFraction12 = bigFractionArr3[3];
        BigFraction[] bigFractionArr4 = {bigFractionAdd.add(bigFraction3.multiply(bigFraction3)), bigFractionAdd2.add(bigFraction6.multiply(bigFraction6)), bigFractionAdd3.add(bigFraction9.multiply(bigFraction9)), bigFractionAdd4.add(bigFraction12.multiply(bigFraction12))};
        BigFraction bigFractionMultiply5 = minor(bigFractionArr, bigFractionArr2, bigFractionArr3).multiply(2);
        BigFraction bigFractionMinor = minor(bigFractionArr4, bigFractionArr2, bigFractionArr3);
        BigFraction bigFractionMinor2 = minor(bigFractionArr4, bigFractionArr, bigFractionArr3);
        BigFraction bigFractionMinor3 = minor(bigFractionArr4, bigFractionArr, bigFractionArr2);
        BigFraction bigFractionDivide = bigFractionMinor.divide(bigFractionMultiply5);
        BigFraction bigFractionNegate = bigFractionMinor2.divide(bigFractionMultiply5).negate();
        BigFraction bigFractionDivide2 = bigFractionMinor3.divide(bigFractionMultiply5);
        BigFraction bigFractionSubtract = bigFractionArr[0].subtract(bigFractionDivide);
        BigFraction bigFractionSubtract2 = bigFractionArr2[0].subtract(bigFractionNegate);
        BigFraction bigFractionSubtract3 = bigFractionArr3[0].subtract(bigFractionDivide2);
        return new EnclosingBall<>(new Vector3D(bigFractionDivide.doubleValue(), bigFractionNegate.doubleValue(), bigFractionDivide2.doubleValue()), FastMath.sqrt(bigFractionSubtract.multiply(bigFractionSubtract).add(bigFractionSubtract2.multiply(bigFractionSubtract2)).add(bigFractionSubtract3.multiply(bigFractionSubtract3)).doubleValue()), vector3D, vector3D2, vector3D3, vector3D4);
    }

    private BigFraction minor(BigFraction[] bigFractionArr, BigFraction[] bigFractionArr2, BigFraction[] bigFractionArr3) {
        return bigFractionArr2[0].multiply(bigFractionArr3[1]).multiply(bigFractionArr[2].subtract(bigFractionArr[3])).add(bigFractionArr2[0].multiply(bigFractionArr3[2]).multiply(bigFractionArr[3].subtract(bigFractionArr[1]))).add(bigFractionArr2[0].multiply(bigFractionArr3[3]).multiply(bigFractionArr[1].subtract(bigFractionArr[2]))).add(bigFractionArr2[1].multiply(bigFractionArr3[0]).multiply(bigFractionArr[3].subtract(bigFractionArr[2]))).add(bigFractionArr2[1].multiply(bigFractionArr3[2]).multiply(bigFractionArr[0].subtract(bigFractionArr[3]))).add(bigFractionArr2[1].multiply(bigFractionArr3[3]).multiply(bigFractionArr[2].subtract(bigFractionArr[0]))).add(bigFractionArr2[2].multiply(bigFractionArr3[0]).multiply(bigFractionArr[1].subtract(bigFractionArr[3]))).add(bigFractionArr2[2].multiply(bigFractionArr3[1]).multiply(bigFractionArr[3].subtract(bigFractionArr[0]))).add(bigFractionArr2[2].multiply(bigFractionArr3[3]).multiply(bigFractionArr[0].subtract(bigFractionArr[1]))).add(bigFractionArr2[3].multiply(bigFractionArr3[0]).multiply(bigFractionArr[2].subtract(bigFractionArr[1]))).add(bigFractionArr2[3].multiply(bigFractionArr3[1]).multiply(bigFractionArr[0].subtract(bigFractionArr[2]))).add(bigFractionArr2[3].multiply(bigFractionArr3[2]).multiply(bigFractionArr[1].subtract(bigFractionArr[0])));
    }
}
