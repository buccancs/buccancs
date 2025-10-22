package org.apache.commons.math.stat.correlation;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.linear.BlockRealMatrix;
import org.apache.commons.math.linear.InvalidMatrixException;
import org.apache.commons.math.linear.MatrixIndexException;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.ranking.NaturalRanking;
import org.apache.commons.math.stat.ranking.RankingAlgorithm;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/stat/correlation/SpearmansCorrelation.class */
public class SpearmansCorrelation {
    private final RealMatrix data;
    private final RankingAlgorithm rankingAlgorithm;
    private final PearsonsCorrelation rankCorrelation;

    public SpearmansCorrelation(RealMatrix dataMatrix, RankingAlgorithm rankingAlgorithm) throws MatrixIndexException, InvalidMatrixException {
        this.data = dataMatrix.copy();
        this.rankingAlgorithm = rankingAlgorithm;
        rankTransform(this.data);
        this.rankCorrelation = new PearsonsCorrelation(this.data);
    }

    public SpearmansCorrelation(RealMatrix dataMatrix) {
        this(dataMatrix, new NaturalRanking());
    }

    public SpearmansCorrelation() {
        this.data = null;
        this.rankingAlgorithm = new NaturalRanking();
        this.rankCorrelation = null;
    }

    public RealMatrix getCorrelationMatrix() {
        return this.rankCorrelation.getCorrelationMatrix();
    }

    public PearsonsCorrelation getRankCorrelation() {
        return this.rankCorrelation;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix matrix) throws MatrixIndexException, InvalidMatrixException {
        RealMatrix matrixCopy = matrix.copy();
        rankTransform(matrixCopy);
        return new PearsonsCorrelation().computeCorrelationMatrix(matrixCopy);
    }

    public RealMatrix computeCorrelationMatrix(double[][] matrix) {
        return computeCorrelationMatrix(new BlockRealMatrix(matrix));
    }

    public double correlation(double[] xArray, double[] yArray) throws IllegalArgumentException {
        if (xArray.length != yArray.length) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(xArray.length), Integer.valueOf(yArray.length));
        }
        if (xArray.length < 2) {
            throw MathRuntimeException.createIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, Integer.valueOf(xArray.length), 2);
        }
        return new PearsonsCorrelation().correlation(this.rankingAlgorithm.rank(xArray), this.rankingAlgorithm.rank(yArray));
    }

    private void rankTransform(RealMatrix matrix) throws MatrixIndexException, InvalidMatrixException {
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            matrix.setColumn(i, this.rankingAlgorithm.rank(matrix.getColumn(i)));
        }
    }
}
