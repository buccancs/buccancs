package org.apache.commons.math.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.math.MathRuntimeException;
import org.apache.commons.math.exception.util.LocalizedFormats;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/random/ValueServer.class */
public class ValueServer {
    public static final int DIGEST_MODE = 0;
    public static final int REPLAY_MODE = 1;
    public static final int UNIFORM_MODE = 2;
    public static final int EXPONENTIAL_MODE = 3;
    public static final int GAUSSIAN_MODE = 4;
    public static final int CONSTANT_MODE = 5;
    private final RandomData randomData;
    private int mode;
    private URL valuesFileURL;
    private double mu;
    private double sigma;
    private EmpiricalDistribution empiricalDistribution;
    private BufferedReader filePointer;

    public ValueServer() {
        this.mode = 5;
        this.valuesFileURL = null;
        this.mu = 0.0d;
        this.sigma = 0.0d;
        this.empiricalDistribution = null;
        this.filePointer = null;
        this.randomData = new RandomDataImpl();
    }

    public ValueServer(RandomData randomData) {
        this.mode = 5;
        this.valuesFileURL = null;
        this.mu = 0.0d;
        this.sigma = 0.0d;
        this.empiricalDistribution = null;
        this.filePointer = null;
        this.randomData = randomData;
    }

    public double getNext() throws IOException {
        switch (this.mode) {
            case 0:
                return getNextDigest();
            case 1:
                return getNextReplay();
            case 2:
                return getNextUniform();
            case 3:
                return getNextExponential();
            case 4:
                return getNextGaussian();
            case 5:
                return this.mu;
            default:
                throw MathRuntimeException.createIllegalStateException(LocalizedFormats.UNKNOWN_MODE, Integer.valueOf(this.mode), "DIGEST_MODE", 0, "REPLAY_MODE", 1, "UNIFORM_MODE", 2, "EXPONENTIAL_MODE", 3, "GAUSSIAN_MODE", 4, "CONSTANT_MODE", 5);
        }
    }

    public void fill(double[] values) throws IOException {
        for (int i = 0; i < values.length; i++) {
            values[i] = getNext();
        }
    }

    public double[] fill(int length) throws IOException {
        double[] out = new double[length];
        for (int i = 0; i < length; i++) {
            out[i] = getNext();
        }
        return out;
    }

    public void computeDistribution() throws IOException {
        this.empiricalDistribution = new EmpiricalDistributionImpl();
        this.empiricalDistribution.load(this.valuesFileURL);
    }

    public void computeDistribution(int binCount) throws IOException {
        this.empiricalDistribution = new EmpiricalDistributionImpl(binCount);
        this.empiricalDistribution.load(this.valuesFileURL);
        this.mu = this.empiricalDistribution.getSampleStats().getMean();
        this.sigma = this.empiricalDistribution.getSampleStats().getStandardDeviation();
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public URL getValuesFileURL() {
        return this.valuesFileURL;
    }

    public void setValuesFileURL(String url) throws MalformedURLException {
        this.valuesFileURL = new URL(url);
    }

    public void setValuesFileURL(URL url) {
        this.valuesFileURL = url;
    }

    public EmpiricalDistribution getEmpiricalDistribution() {
        return this.empiricalDistribution;
    }

    public void resetReplayFile() throws IOException {
        if (this.filePointer != null) {
            try {
                this.filePointer.close();
                this.filePointer = null;
            } catch (IOException e) {
            }
        }
        this.filePointer = new BufferedReader(new InputStreamReader(this.valuesFileURL.openStream()));
    }

    public void closeReplayFile() throws IOException {
        if (this.filePointer != null) {
            this.filePointer.close();
            this.filePointer = null;
        }
    }

    public double getMu() {
        return this.mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getSigma() {
        return this.sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    private double getNextDigest() {
        if (this.empiricalDistribution == null || this.empiricalDistribution.getBinStats().size() == 0) {
            throw MathRuntimeException.createIllegalStateException(LocalizedFormats.DIGEST_NOT_INITIALIZED, new Object[0]);
        }
        return this.empiricalDistribution.getNextValue();
    }

    private double getNextReplay() throws IOException {
        if (this.filePointer == null) {
            resetReplayFile();
        }
        String line = this.filePointer.readLine();
        String str = line;
        if (line == null) {
            closeReplayFile();
            resetReplayFile();
            String line2 = this.filePointer.readLine();
            str = line2;
            if (line2 == null) {
                throw MathRuntimeException.createEOFException(LocalizedFormats.URL_CONTAINS_NO_DATA, this.valuesFileURL);
            }
        }
        return Double.valueOf(str).doubleValue();
    }

    private double getNextUniform() {
        return this.randomData.nextUniform(0.0d, 2.0d * this.mu);
    }

    private double getNextExponential() {
        return this.randomData.nextExponential(this.mu);
    }

    private double getNextGaussian() {
        return this.randomData.nextGaussian(this.mu, this.sigma);
    }
}
