package org.apache.commons.math.random;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Collection;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.BetaDistributionImpl;
import org.apache.commons.math.distribution.BinomialDistributionImpl;
import org.apache.commons.math.distribution.CauchyDistributionImpl;
import org.apache.commons.math.distribution.ChiSquaredDistributionImpl;
import org.apache.commons.math.distribution.ContinuousDistribution;
import org.apache.commons.math.distribution.FDistributionImpl;
import org.apache.commons.math.distribution.GammaDistributionImpl;
import org.apache.commons.math.distribution.HypergeometricDistributionImpl;
import org.apache.commons.math.distribution.IntegerDistribution;
import org.apache.commons.math.distribution.PascalDistributionImpl;
import org.apache.commons.math.distribution.TDistributionImpl;
import org.apache.commons.math.distribution.WeibullDistributionImpl;
import org.apache.commons.math.distribution.ZipfDistributionImpl;
import org.apache.commons.math.exception.MathInternalError;
import org.apache.commons.math.exception.NotStrictlyPositiveException;
import org.apache.commons.math.exception.NumberIsTooLargeException;
import org.apache.commons.math.exception.util.LocalizedFormats;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/RandomDataImpl.class */
public class RandomDataImpl implements RandomData, Serializable {
    private static final long serialVersionUID = -626730818244969716L;
    private RandomGenerator rand;
    private SecureRandom secRand;

    public RandomDataImpl() {
        this.rand = null;
        this.secRand = null;
    }

    public RandomDataImpl(RandomGenerator rand) {
        this.rand = null;
        this.secRand = null;
        this.rand = rand;
    }

    @Override // org.apache.commons.math.random.RandomData
    public String nextHexString(int len) {
        if (len <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(len));
        }
        RandomGenerator ran = getRan();
        StringBuilder outBuffer = new StringBuilder();
        byte[] randomBytes = new byte[(len / 2) + 1];
        ran.nextBytes(randomBytes);
        for (byte b : randomBytes) {
            Integer c = Integer.valueOf(b);
            String hex = Integer.toHexString(c.intValue() + 128);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            outBuffer.append(hex);
        }
        return outBuffer.toString().substring(0, len);
    }

    @Override // org.apache.commons.math.random.RandomData
    public int nextInt(int lower, int upper) {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(lower), Integer.valueOf(upper), false);
        }
        double r = getRan().nextDouble();
        return (int) ((r * upper) + ((1.0d - r) * lower) + r);
    }

    @Override // org.apache.commons.math.random.RandomData
    public long nextLong(long lower, long upper) {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(lower), Long.valueOf(upper), false);
        }
        double r = getRan().nextDouble();
        return (long) ((r * upper) + ((1.0d - r) * lower) + r);
    }

    @Override // org.apache.commons.math.random.RandomData
    public String nextSecureHexString(int len) throws NoSuchAlgorithmException {
        if (len <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(len));
        }
        SecureRandom secRan = getSecRan();
        try {
            MessageDigest alg = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            alg.reset();
            int numIter = (len / 40) + 1;
            StringBuilder outBuffer = new StringBuilder();
            for (int iter = 1; iter < numIter + 1; iter++) {
                byte[] randomBytes = new byte[40];
                secRan.nextBytes(randomBytes);
                alg.update(randomBytes);
                byte[] hash = alg.digest();
                for (byte b : hash) {
                    Integer c = Integer.valueOf(b);
                    String hex = Integer.toHexString(c.intValue() + 128);
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    outBuffer.append(hex);
                }
            }
            return outBuffer.toString().substring(0, len);
        } catch (NoSuchAlgorithmException ex) {
            throw new MathInternalError(ex);
        }
    }

    @Override // org.apache.commons.math.random.RandomData
    public int nextSecureInt(int lower, int upper) {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(lower), Integer.valueOf(upper), false);
        }
        SecureRandom sec = getSecRan();
        return lower + ((int) (sec.nextDouble() * ((upper - lower) + 1)));
    }

    @Override // org.apache.commons.math.random.RandomData
    public long nextSecureLong(long lower, long upper) {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(lower), Long.valueOf(upper), false);
        }
        SecureRandom sec = getSecRan();
        return lower + ((long) (sec.nextDouble() * ((upper - lower) + 1)));
    }

    @Override // org.apache.commons.math.random.RandomData
    public long nextPoisson(double mean) {
        double y;
        double x;
        double y2;
        double v;
        if (mean <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean));
        }
        RandomGenerator generator = getRan();
        if (mean < 40.0d) {
            double p = FastMath.exp(-mean);
            long n = 0;
            double r = 1.0d;
            while (n < 1000.0d * mean) {
                double rnd = generator.nextDouble();
                r *= rnd;
                if (r >= p) {
                    n++;
                } else {
                    return n;
                }
            }
            return n;
        }
        double lambda = FastMath.floor(mean);
        double lambdaFractional = mean - lambda;
        double logLambda = FastMath.log(lambda);
        double logLambdaFactorial = MathUtils.factorialLog((int) lambda);
        long y22 = lambdaFractional < Double.MIN_VALUE ? 0L : nextPoisson(lambdaFractional);
        double delta = FastMath.sqrt(lambda * FastMath.log(((32.0d * lambda) / 3.141592653589793d) + 1.0d));
        double halfDelta = delta / 2.0d;
        double twolpd = (2.0d * lambda) + delta;
        double a1 = FastMath.sqrt(3.141592653589793d * twolpd) * FastMath.exp(0.0d * lambda);
        double a2 = (twolpd / delta) * FastMath.exp(((-delta) * (1.0d + delta)) / twolpd);
        double aSum = a1 + a2 + 1.0d;
        double p1 = a1 / aSum;
        double p2 = a2 / aSum;
        double c1 = 1.0d / (8.0d * lambda);
        while (true) {
            double u = nextUniform(0.0d, 1.0d);
            if (u <= p1) {
                double n2 = nextGaussian(0.0d, 1.0d);
                x = (n2 * FastMath.sqrt(lambda + halfDelta)) - 0.5d;
                if (x <= delta && x >= (-lambda)) {
                    y2 = x < 0.0d ? FastMath.floor(x) : FastMath.ceil(x);
                    double e = nextExponential(1.0d);
                    v = ((-e) - ((n2 * n2) / 2.0d)) + c1;
                }
            } else {
                if (u > p1 + p2) {
                    y = lambda;
                    break;
                }
                x = delta + ((twolpd / delta) * nextExponential(1.0d));
                y2 = FastMath.ceil(x);
                v = (-nextExponential(1.0d)) - ((delta * (x + 1.0d)) / twolpd);
            }
            int a = x < 0.0d ? 1 : 0;
            double t = (y2 * (y2 + 1.0d)) / (2.0d * lambda);
            if (v < (-t) && a == 0) {
                y = lambda + y2;
                break;
            }
            double qr = t * ((((2.0d * y2) + 1.0d) / (6.0d * lambda)) - 1.0d);
            double qa = qr - ((t * t) / (3.0d * (lambda + (a * (y2 + 1.0d)))));
            if (v < qa) {
                y = lambda + y2;
                break;
            }
            if (v <= qr && v < ((y2 * logLambda) - MathUtils.factorialLog((int) (y2 + lambda))) + logLambdaFactorial) {
                y = lambda + y2;
                break;
            }
        }
        return y22 + ((long) y);
    }

    @Override // org.apache.commons.math.random.RandomData
    public double nextGaussian(double mu, double sigma) {
        if (sigma <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(sigma));
        }
        return (sigma * getRan().nextGaussian()) + mu;
    }

    @Override // org.apache.commons.math.random.RandomData
    public double nextExponential(double mean) {
        if (mean <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean));
        }
        RandomGenerator generator = getRan();
        double dNextDouble = generator.nextDouble();
        while (true) {
            double unif = dNextDouble;
            if (unif == 0.0d) {
                dNextDouble = generator.nextDouble();
            } else {
                return (-mean) * FastMath.log(unif);
            }
        }
    }

    @Override // org.apache.commons.math.random.RandomData
    public double nextUniform(double lower, double upper) {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(lower), Double.valueOf(upper), false);
        }
        RandomGenerator generator = getRan();
        double dNextDouble = generator.nextDouble();
        while (true) {
            double u = dNextDouble;
            if (u <= 0.0d) {
                dNextDouble = generator.nextDouble();
            } else {
                return lower + (u * (upper - lower));
            }
        }
    }

    public double nextBeta(double alpha, double beta) throws MathException {
        return nextInversionDeviate(new BetaDistributionImpl(alpha, beta));
    }

    public int nextBinomial(int numberOfTrials, double probabilityOfSuccess) throws MathException {
        return nextInversionDeviate(new BinomialDistributionImpl(numberOfTrials, probabilityOfSuccess));
    }

    public double nextCauchy(double median, double scale) throws MathException {
        return nextInversionDeviate(new CauchyDistributionImpl(median, scale));
    }

    public double nextChiSquare(double df) throws MathException {
        return nextInversionDeviate(new ChiSquaredDistributionImpl(df));
    }

    public double nextF(double numeratorDf, double denominatorDf) throws MathException {
        return nextInversionDeviate(new FDistributionImpl(numeratorDf, denominatorDf));
    }

    public double nextGamma(double shape, double scale) throws MathException {
        return nextInversionDeviate(new GammaDistributionImpl(shape, scale));
    }

    public int nextHypergeometric(int populationSize, int numberOfSuccesses, int sampleSize) throws MathException {
        return nextInversionDeviate(new HypergeometricDistributionImpl(populationSize, numberOfSuccesses, sampleSize));
    }

    public int nextPascal(int r, double p) throws MathException {
        return nextInversionDeviate(new PascalDistributionImpl(r, p));
    }

    public double nextT(double df) throws MathException {
        return nextInversionDeviate(new TDistributionImpl(df));
    }

    public double nextWeibull(double shape, double scale) throws MathException {
        return nextInversionDeviate(new WeibullDistributionImpl(shape, scale));
    }

    public int nextZipf(int numberOfElements, double exponent) throws MathException {
        return nextInversionDeviate(new ZipfDistributionImpl(numberOfElements, exponent));
    }

    private RandomGenerator getRan() {
        if (this.rand == null) {
            this.rand = new JDKRandomGenerator();
            this.rand.setSeed(System.currentTimeMillis());
        }
        return this.rand;
    }

    private SecureRandom getSecRan() {
        if (this.secRand == null) {
            this.secRand = new SecureRandom();
            this.secRand.setSeed(System.currentTimeMillis());
        }
        return this.secRand;
    }

    public void reSeed(long seed) {
        if (this.rand == null) {
            this.rand = new JDKRandomGenerator();
        }
        this.rand.setSeed(seed);
    }

    public void reSeedSecure() {
        if (this.secRand == null) {
            this.secRand = new SecureRandom();
        }
        this.secRand.setSeed(System.currentTimeMillis());
    }

    public void reSeedSecure(long seed) {
        if (this.secRand == null) {
            this.secRand = new SecureRandom();
        }
        this.secRand.setSeed(seed);
    }

    public void reSeed() {
        if (this.rand == null) {
            this.rand = new JDKRandomGenerator();
        }
        this.rand.setSeed(System.currentTimeMillis());
    }

    public void setSecureAlgorithm(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.secRand = SecureRandom.getInstance(algorithm, provider);
    }

    @Override // org.apache.commons.math.random.RandomData
    public int[] nextPermutation(int n, int k) {
        if (k > n) {
            throw new NumberIsTooLargeException(LocalizedFormats.PERMUTATION_EXCEEDS_N, Integer.valueOf(k), Integer.valueOf(n), true);
        }
        if (k == 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.PERMUTATION_SIZE, Integer.valueOf(k));
        }
        int[] index = getNatural(n);
        shuffle(index, n - k);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = index[(n - i) - 1];
        }
        return result;
    }

    @Override // org.apache.commons.math.random.RandomData
    public Object[] nextSample(Collection<?> c, int k) {
        int len = c.size();
        if (k > len) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, Integer.valueOf(k), Integer.valueOf(len), true);
        }
        if (k <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(k));
        }
        Object[] objects = c.toArray();
        int[] index = nextPermutation(len, k);
        Object[] result = new Object[k];
        for (int i = 0; i < k; i++) {
            result[i] = objects[index[i]];
        }
        return result;
    }

    public double nextInversionDeviate(ContinuousDistribution distribution) throws MathException {
        return distribution.inverseCumulativeProbability(nextUniform(0.0d, 1.0d));
    }

    public int nextInversionDeviate(IntegerDistribution distribution) throws MathException {
        double target = nextUniform(0.0d, 1.0d);
        int glb = distribution.inverseCumulativeProbability(target);
        if (distribution.cumulativeProbability(glb) == 1.0d) {
            return glb;
        }
        return glb + 1;
    }

    private void shuffle(int[] list, int end) {
        int iNextInt;
        for (int i = list.length - 1; i >= end; i--) {
            if (i == 0) {
                iNextInt = 0;
            } else {
                iNextInt = nextInt(0, i);
            }
            int target = iNextInt;
            int temp = list[target];
            list[target] = list[i];
            list[i] = temp;
        }
    }

    private int[] getNatural(int n) {
        int[] natural = new int[n];
        for (int i = 0; i < n; i++) {
            natural[i] = i;
        }
        return natural;
    }
}
