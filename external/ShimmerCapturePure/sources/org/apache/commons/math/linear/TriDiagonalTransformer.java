package org.apache.commons.math.linear;

import java.util.Arrays;

import org.apache.commons.math.util.FastMath;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/linear/TriDiagonalTransformer.class */
class TriDiagonalTransformer {
    private final double[][] householderVectors;
    private final double[] main;
    private final double[] secondary;
    private RealMatrix cachedQ;
    private RealMatrix cachedQt;
    private RealMatrix cachedT;

    public TriDiagonalTransformer(RealMatrix matrix) throws InvalidMatrixException {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int m = matrix.getRowDimension();
        this.householderVectors = matrix.getData();
        this.main = new double[m];
        this.secondary = new double[m - 1];
        this.cachedQ = null;
        this.cachedQt = null;
        this.cachedT = null;
        transform();
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() throws MatrixIndexException {
        if (this.cachedQt == null) {
            int m = this.householderVectors.length;
            this.cachedQt = MatrixUtils.createRealMatrix(m, m);
            for (int k = m - 1; k >= 1; k--) {
                double[] hK = this.householderVectors[k - 1];
                double inv = 1.0d / (this.secondary[k - 1] * hK[k]);
                this.cachedQt.setEntry(k, k, 1.0d);
                if (hK[k] != 0.0d) {
                    double beta = 1.0d / this.secondary[k - 1];
                    this.cachedQt.setEntry(k, k, 1.0d + (beta * hK[k]));
                    for (int i = k + 1; i < m; i++) {
                        this.cachedQt.setEntry(k, i, beta * hK[i]);
                    }
                    for (int j = k + 1; j < m; j++) {
                        double beta2 = 0.0d;
                        for (int i2 = k + 1; i2 < m; i2++) {
                            beta2 += this.cachedQt.getEntry(j, i2) * hK[i2];
                        }
                        double beta3 = beta2 * inv;
                        this.cachedQt.setEntry(j, k, beta3 * hK[k]);
                        for (int i3 = k + 1; i3 < m; i3++) {
                            this.cachedQt.addToEntry(j, i3, beta3 * hK[i3]);
                        }
                    }
                }
            }
            this.cachedQt.setEntry(0, 0, 1.0d);
        }
        return this.cachedQt;
    }

    public RealMatrix getT() throws MatrixIndexException {
        if (this.cachedT == null) {
            int m = this.main.length;
            this.cachedT = MatrixUtils.createRealMatrix(m, m);
            for (int i = 0; i < m; i++) {
                this.cachedT.setEntry(i, i, this.main[i]);
                if (i > 0) {
                    this.cachedT.setEntry(i, i - 1, this.secondary[i - 1]);
                }
                if (i < this.main.length - 1) {
                    this.cachedT.setEntry(i, i + 1, this.secondary[i]);
                }
            }
        }
        return this.cachedT;
    }

    double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    double[] getMainDiagonalRef() {
        return this.main;
    }

    double[] getSecondaryDiagonalRef() {
        return this.secondary;
    }

    private void transform() {
        int m = this.householderVectors.length;
        double[] z = new double[m];
        for (int k = 0; k < m - 1; k++) {
            double[] hK = this.householderVectors[k];
            this.main[k] = hK[k];
            double xNormSqr = 0.0d;
            for (int j = k + 1; j < m; j++) {
                double c = hK[j];
                xNormSqr += c * c;
            }
            double a = hK[k + 1] > 0.0d ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.secondary[k] = a;
            if (a != 0.0d) {
                int i = k + 1;
                hK[i] = hK[i] - a;
                double beta = (-1.0d) / (a * hK[k + 1]);
                Arrays.fill(z, k + 1, m, 0.0d);
                for (int i2 = k + 1; i2 < m; i2++) {
                    double[] hI = this.householderVectors[i2];
                    double hKI = hK[i2];
                    double zI = hI[i2] * hKI;
                    for (int j2 = i2 + 1; j2 < m; j2++) {
                        double hIJ = hI[j2];
                        zI += hIJ * hK[j2];
                        int i3 = j2;
                        z[i3] = z[i3] + (hIJ * hKI);
                    }
                    z[i2] = beta * (z[i2] + zI);
                }
                double gamma = 0.0d;
                for (int i4 = k + 1; i4 < m; i4++) {
                    gamma += z[i4] * hK[i4];
                }
                double gamma2 = gamma * (beta / 2.0d);
                for (int i5 = k + 1; i5 < m; i5++) {
                    int i6 = i5;
                    z[i6] = z[i6] - (gamma2 * hK[i5]);
                }
                for (int i7 = k + 1; i7 < m; i7++) {
                    double[] hI2 = this.householderVectors[i7];
                    for (int j3 = i7; j3 < m; j3++) {
                        int i8 = j3;
                        hI2[i8] = hI2[i8] - ((hK[i7] * z[j3]) + (z[i7] * hK[j3]));
                    }
                }
            }
        }
        this.main[m - 1] = this.householderVectors[m - 1][m - 1];
    }
}
