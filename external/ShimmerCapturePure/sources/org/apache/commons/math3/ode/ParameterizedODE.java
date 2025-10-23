package org.apache.commons.math3.ode;

/* loaded from: classes5.dex */
public interface ParameterizedODE extends Parameterizable {
    double getParameter(String str) throws UnknownParameterException;

    void setParameter(String str, double d) throws UnknownParameterException;
}
