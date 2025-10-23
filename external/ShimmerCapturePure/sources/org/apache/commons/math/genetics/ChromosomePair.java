package org.apache.commons.math.genetics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: ShimmerCapture_1.3.1_APKPure.apk:libs/commons-math-2.2.jar:org/apache/commons/math/genetics/ChromosomePair.class */
public class ChromosomePair {
    private final Chromosome first;
    private final Chromosome second;

    public ChromosomePair(Chromosome c1, Chromosome c2) {
        this.first = c1;
        this.second = c2;
    }

    public Chromosome getFirst() {
        return this.first;
    }

    public Chromosome getSecond() {
        return this.second;
    }

    public String toString() {
        return String.format("(%s,%s)", getFirst(), getSecond());
    }
}
