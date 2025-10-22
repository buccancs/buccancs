package org.apache.commons.math3.random;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collection;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

@Deprecated
/* loaded from: classes5.dex */
public class RandomDataImpl implements RandomData, Serializable {
    private static final long serialVersionUID = -626730818244969716L;
    private final RandomDataGenerator delegate;

    public RandomDataImpl() {
        this.delegate = new RandomDataGenerator();
    }

    public RandomDataImpl(RandomGenerator randomGenerator) {
        this.delegate = new RandomDataGenerator(randomGenerator);
    }

    @Deprecated
    RandomDataGenerator getDelegate() {
        return this.delegate;
    }

    @Override // org.apache.commons.math3.random.RandomData
    public String nextHexString(int i) throws NotStrictlyPositiveException {
        return this.delegate.nextHexString(i);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public int nextInt(int i, int i2) throws NumberIsTooLargeException {
        return this.delegate.nextInt(i, i2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public long nextLong(long j, long j2) throws NumberIsTooLargeException {
        return this.delegate.nextLong(j, j2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public String nextSecureHexString(int i) throws NotStrictlyPositiveException {
        return this.delegate.nextSecureHexString(i);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public int nextSecureInt(int i, int i2) throws NumberIsTooLargeException {
        return this.delegate.nextSecureInt(i, i2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public long nextSecureLong(long j, long j2) throws NumberIsTooLargeException {
        return this.delegate.nextSecureLong(j, j2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public long nextPoisson(double d) throws NotStrictlyPositiveException {
        return this.delegate.nextPoisson(d);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public double nextGaussian(double d, double d2) throws NotStrictlyPositiveException {
        return this.delegate.nextGaussian(d, d2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public double nextExponential(double d) throws NotStrictlyPositiveException {
        return this.delegate.nextExponential(d);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public double nextUniform(double d, double d2) throws NotANumberException, NotFiniteNumberException, NumberIsTooLargeException {
        return this.delegate.nextUniform(d, d2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public double nextUniform(double d, double d2, boolean z) throws NotANumberException, NotFiniteNumberException, NumberIsTooLargeException {
        return this.delegate.nextUniform(d, d2, z);
    }

    public double nextBeta(double d, double d2) {
        return this.delegate.nextBeta(d, d2);
    }

    public int nextBinomial(int i, double d) {
        return this.delegate.nextBinomial(i, d);
    }

    public double nextCauchy(double d, double d2) {
        return this.delegate.nextCauchy(d, d2);
    }

    public double nextChiSquare(double d) {
        return this.delegate.nextChiSquare(d);
    }

    public double nextF(double d, double d2) throws NotStrictlyPositiveException {
        return this.delegate.nextF(d, d2);
    }

    public double nextGamma(double d, double d2) throws NotStrictlyPositiveException {
        return this.delegate.nextGamma(d, d2);
    }

    public int nextHypergeometric(int i, int i2, int i3) throws NotStrictlyPositiveException, NotPositiveException, NumberIsTooLargeException {
        return this.delegate.nextHypergeometric(i, i2, i3);
    }

    public int nextPascal(int i, double d) throws OutOfRangeException, NotStrictlyPositiveException {
        return this.delegate.nextPascal(i, d);
    }

    public double nextT(double d) throws NotStrictlyPositiveException {
        return this.delegate.nextT(d);
    }

    public double nextWeibull(double d, double d2) throws NotStrictlyPositiveException {
        return this.delegate.nextWeibull(d, d2);
    }

    public int nextZipf(int i, double d) throws NotStrictlyPositiveException {
        return this.delegate.nextZipf(i, d);
    }

    public void reSeed(long j) {
        this.delegate.reSeed(j);
    }

    public void reSeedSecure() {
        this.delegate.reSeedSecure();
    }

    public void reSeedSecure(long j) {
        this.delegate.reSeedSecure(j);
    }

    public void reSeed() {
        this.delegate.reSeed();
    }

    public void setSecureAlgorithm(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.delegate.setSecureAlgorithm(str, str2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public int[] nextPermutation(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return this.delegate.nextPermutation(i, i2);
    }

    @Override // org.apache.commons.math3.random.RandomData
    public Object[] nextSample(Collection<?> collection, int i) throws NotStrictlyPositiveException, NumberIsTooLargeException {
        return this.delegate.nextSample(collection, i);
    }

    @Deprecated
    public double nextInversionDeviate(RealDistribution realDistribution) throws MathIllegalArgumentException {
        return realDistribution.inverseCumulativeProbability(nextUniform(0.0d, 1.0d));
    }

    @Deprecated
    public int nextInversionDeviate(IntegerDistribution integerDistribution) throws MathIllegalArgumentException {
        return integerDistribution.inverseCumulativeProbability(nextUniform(0.0d, 1.0d));
    }
}
