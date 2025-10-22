package org.apache.commons.math3.genetics;

/* loaded from: classes5.dex */
public class ChromosomePair {
    private final Chromosome first;
    private final Chromosome second;

    public ChromosomePair(Chromosome chromosome, Chromosome chromosome2) {
        this.first = chromosome;
        this.second = chromosome2;
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
