package org.apache.commons.math3.util;

import java.util.EventObject;

/* loaded from: classes5.dex */
public class IterationEvent extends EventObject {
    private static final long serialVersionUID = 20120128;
    private final int iterations;

    public IterationEvent(Object obj, int i) {
        super(obj);
        this.iterations = i;
    }

    public int getIterations() {
        return this.iterations;
    }
}
