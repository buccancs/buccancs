package org.bouncycastle.crypto.tls;

import java.math.BigInteger;
import java.util.Vector;

import org.bouncycastle.crypto.agreement.DHStandardGroups;
import org.bouncycastle.crypto.params.DHParameters;

/* loaded from: classes5.dex */
public class DefaultTlsDHVerifier implements TlsDHVerifier {
    public static final int DEFAULT_MINIMUM_PRIME_BITS = 2048;
    protected static final Vector DEFAULT_GROUPS = new Vector();

    static {
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe2048);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe3072);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe4096);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe6144);
        addDefaultGroup(DHStandardGroups.rfc7919_ffdhe8192);
        addDefaultGroup(DHStandardGroups.rfc3526_1536);
        addDefaultGroup(DHStandardGroups.rfc3526_2048);
        addDefaultGroup(DHStandardGroups.rfc3526_3072);
        addDefaultGroup(DHStandardGroups.rfc3526_4096);
        addDefaultGroup(DHStandardGroups.rfc3526_6144);
        addDefaultGroup(DHStandardGroups.rfc3526_8192);
    }

    protected Vector groups;
    protected int minimumPrimeBits;

    public DefaultTlsDHVerifier() {
        this(2048);
    }

    public DefaultTlsDHVerifier(int i) {
        this(DEFAULT_GROUPS, i);
    }

    public DefaultTlsDHVerifier(Vector vector, int i) {
        this.groups = vector;
        this.minimumPrimeBits = i;
    }

    private static void addDefaultGroup(DHParameters dHParameters) {
        DEFAULT_GROUPS.addElement(dHParameters);
    }

    @Override // org.bouncycastle.crypto.tls.TlsDHVerifier
    public boolean accept(DHParameters dHParameters) {
        return checkMinimumPrimeBits(dHParameters) && checkGroup(dHParameters);
    }

    protected boolean areGroupsEqual(DHParameters dHParameters, DHParameters dHParameters2) {
        return dHParameters == dHParameters2 || (areParametersEqual(dHParameters.getP(), dHParameters2.getP()) && areParametersEqual(dHParameters.getG(), dHParameters2.getG()));
    }

    protected boolean areParametersEqual(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger == bigInteger2 || bigInteger.equals(bigInteger2);
    }

    protected boolean checkGroup(DHParameters dHParameters) {
        for (int i = 0; i < this.groups.size(); i++) {
            if (areGroupsEqual(dHParameters, (DHParameters) this.groups.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkMinimumPrimeBits(DHParameters dHParameters) {
        return dHParameters.getP().bitLength() >= getMinimumPrimeBits();
    }

    public int getMinimumPrimeBits() {
        return this.minimumPrimeBits;
    }
}
