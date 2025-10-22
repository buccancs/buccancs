package org.bouncycastle.pkcs;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.Integers;

/* loaded from: classes5.dex */
class PKCSUtils {
    private static final Map PRFS_SALT;

    static {
        HashMap map = new HashMap();
        PRFS_SALT = map;
        map.put(PKCSObjectIdentifiers.id_hmacWithSHA1, Integers.valueOf(20));
        map.put(PKCSObjectIdentifiers.id_hmacWithSHA256, Integers.valueOf(32));
        map.put(PKCSObjectIdentifiers.id_hmacWithSHA512, Integers.valueOf(64));
        map.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(28));
        map.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(48));
        map.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(28));
        map.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, Integers.valueOf(32));
        map.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(48));
        map.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, Integers.valueOf(64));
        map.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(32));
    }

    PKCSUtils() {
    }

    static int getSaltSize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Map map = PRFS_SALT;
        if (map.containsKey(aSN1ObjectIdentifier)) {
            return ((Integer) map.get(aSN1ObjectIdentifier)).intValue();
        }
        throw new IllegalStateException("no salt size for algorithm: " + aSN1ObjectIdentifier);
    }
}
