package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.agreement.kdf.DHKDFParameters;
import org.bouncycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public abstract class BaseAgreementSpi extends KeyAgreementSpi {
    private static final Map<String, ASN1ObjectIdentifier> defaultOids;
    private static final Hashtable des;
    private static final Map<String, Integer> keySizes;
    private static final Map<String, String> nameTable;
    private static final Hashtable oids;

    static {
        HashMap map = new HashMap();
        defaultOids = map;
        HashMap map2 = new HashMap();
        keySizes = map2;
        HashMap map3 = new HashMap();
        nameTable = map3;
        Hashtable hashtable = new Hashtable();
        oids = hashtable;
        Hashtable hashtable2 = new Hashtable();
        des = hashtable2;
        Integer numValueOf = Integers.valueOf(64);
        Integer numValueOf2 = Integers.valueOf(128);
        Integer numValueOf3 = Integers.valueOf(192);
        Integer numValueOf4 = Integers.valueOf(256);
        map2.put("DES", numValueOf);
        map2.put("DESEDE", numValueOf3);
        map2.put("BLOWFISH", numValueOf2);
        map2.put("AES", numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_ECB.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_ECB.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_ECB.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_CFB.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_CFB.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_CFB.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_OFB.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_OFB.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_OFB.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_wrap.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_wrap.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_CCM.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_CCM.getId(), numValueOf4);
        map2.put(NISTObjectIdentifiers.id_aes128_GCM.getId(), numValueOf2);
        map2.put(NISTObjectIdentifiers.id_aes192_GCM.getId(), numValueOf3);
        map2.put(NISTObjectIdentifiers.id_aes256_GCM.getId(), numValueOf4);
        map2.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), numValueOf2);
        map2.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), numValueOf3);
        map2.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), numValueOf4);
        map2.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), numValueOf2);
        map2.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), numValueOf3);
        map2.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), numValueOf3);
        map2.put(OIWObjectIdentifiers.desCBC.getId(), numValueOf);
        map2.put(CryptoProObjectIdentifiers.gostR28147_gcfb.getId(), numValueOf4);
        map2.put(CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap.getId(), numValueOf4);
        map2.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap.getId(), numValueOf4);
        map2.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), Integers.valueOf(160));
        map2.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), numValueOf4);
        map2.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), Integers.valueOf(384));
        map2.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), Integers.valueOf(512));
        map.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        map.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        map.put("CAMELLIA", NTTObjectIdentifiers.id_camellia256_cbc);
        map.put("SEED", KISAObjectIdentifiers.id_seedCBC);
        map.put("DES", OIWObjectIdentifiers.desCBC);
        map3.put(MiscObjectIdentifiers.cast5CBC.getId(), "CAST5");
        map3.put(MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC.getId(), "IDEA");
        map3.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_ECB.getId(), "Blowfish");
        map3.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CBC.getId(), "Blowfish");
        map3.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CFB.getId(), "Blowfish");
        map3.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_OFB.getId(), "Blowfish");
        map3.put(OIWObjectIdentifiers.desECB.getId(), "DES");
        map3.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        map3.put(OIWObjectIdentifiers.desCFB.getId(), "DES");
        map3.put(OIWObjectIdentifiers.desOFB.getId(), "DES");
        map3.put(OIWObjectIdentifiers.desEDE.getId(), "DESede");
        map3.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DESede");
        map3.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DESede");
        map3.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap.getId(), "RC2");
        map3.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), "HmacSHA1");
        map3.put(PKCSObjectIdentifiers.id_hmacWithSHA224.getId(), "HmacSHA224");
        map3.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), "HmacSHA256");
        map3.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), "HmacSHA384");
        map3.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), "HmacSHA512");
        map3.put(NTTObjectIdentifiers.id_camellia128_cbc.getId(), "Camellia");
        map3.put(NTTObjectIdentifiers.id_camellia192_cbc.getId(), "Camellia");
        map3.put(NTTObjectIdentifiers.id_camellia256_cbc.getId(), "Camellia");
        map3.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), "Camellia");
        map3.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), "Camellia");
        map3.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), "Camellia");
        map3.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), "SEED");
        map3.put(KISAObjectIdentifiers.id_seedCBC.getId(), "SEED");
        map3.put(KISAObjectIdentifiers.id_seedMAC.getId(), "SEED");
        map3.put(CryptoProObjectIdentifiers.gostR28147_gcfb.getId(), "GOST28147");
        map3.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), "AES");
        map3.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        map3.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        hashtable.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        hashtable.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        hashtable.put("DES", OIWObjectIdentifiers.desCBC);
        hashtable2.put("DES", "DES");
        hashtable2.put("DESEDE", "DES");
        hashtable2.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        hashtable2.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DES");
        hashtable2.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DES");
    }

    protected final String kaAlgorithm;
    protected final DerivationFunction kdf;
    protected byte[] ukmParameters;

    public BaseAgreementSpi(String str, DerivationFunction derivationFunction) {
        this.kaAlgorithm = str;
        this.kdf = derivationFunction;
    }

    protected static String getAlgorithm(String str) {
        if (str.indexOf(91) > 0) {
            return str.substring(0, str.indexOf(91));
        }
        if (str.startsWith(NISTObjectIdentifiers.aes.getId())) {
            return "AES";
        }
        if (str.startsWith(GNUObjectIdentifiers.Serpent.getId())) {
            return "Serpent";
        }
        String str2 = nameTable.get(Strings.toUpperCase(str));
        return str2 != null ? str2 : str;
    }

    protected static int getKeySize(String str) {
        if (str.indexOf(91) > 0) {
            return Integer.parseInt(str.substring(str.indexOf(91) + 1, str.indexOf(93)));
        }
        String upperCase = Strings.toUpperCase(str);
        Map<String, Integer> map = keySizes;
        if (map.containsKey(upperCase)) {
            return map.get(upperCase).intValue();
        }
        return -1;
    }

    protected static byte[] trimZeroes(byte[] bArr) {
        if (bArr[0] != 0) {
            return bArr;
        }
        int i = 0;
        while (i < bArr.length && bArr[i] == 0) {
            i++;
        }
        int length = bArr.length - i;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i, bArr2, 0, length);
        return bArr2;
    }

    private byte[] getSharedSecretBytes(byte[] bArr, String str, int i) throws DataLengthException, NoSuchAlgorithmException, IllegalArgumentException {
        DerivationParameters kDFParameters;
        DerivationFunction derivationFunction = this.kdf;
        if (derivationFunction == null) {
            if (i <= 0) {
                return bArr;
            }
            int i2 = i / 8;
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            Arrays.clear(bArr);
            return bArr2;
        }
        if (i < 0) {
            throw new NoSuchAlgorithmException("unknown algorithm encountered: " + str);
        }
        int i3 = i / 8;
        byte[] bArr3 = new byte[i3];
        if (!(derivationFunction instanceof DHKEKGenerator)) {
            kDFParameters = new KDFParameters(bArr, this.ukmParameters);
        } else {
            if (str == null) {
                throw new NoSuchAlgorithmException("algorithm OID is null");
            }
            try {
                kDFParameters = new DHKDFParameters(new ASN1ObjectIdentifier(str), i, bArr, this.ukmParameters);
            } catch (IllegalArgumentException unused) {
                throw new NoSuchAlgorithmException("no OID for algorithm: " + str);
            }
        }
        this.kdf.init(kDFParameters);
        this.kdf.generateBytes(bArr3, 0, i3);
        Arrays.clear(bArr);
        return bArr3;
    }

    protected abstract byte[] calcSecret();

    @Override // javax.crypto.KeyAgreementSpi
    protected int engineGenerateSecret(byte[] bArr, int i) throws IllegalStateException, ShortBufferException {
        byte[] bArrEngineGenerateSecret = engineGenerateSecret();
        if (bArr.length - i >= bArrEngineGenerateSecret.length) {
            System.arraycopy(bArrEngineGenerateSecret, 0, bArr, i, bArrEngineGenerateSecret.length);
            return bArrEngineGenerateSecret.length;
        }
        throw new ShortBufferException(this.kaAlgorithm + " key agreement: need " + bArrEngineGenerateSecret.length + " bytes");
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected SecretKey engineGenerateSecret(String str) throws DataLengthException, NoSuchAlgorithmException, IllegalArgumentException {
        String upperCase = Strings.toUpperCase(str);
        Hashtable hashtable = oids;
        String id = hashtable.containsKey(upperCase) ? ((ASN1ObjectIdentifier) hashtable.get(upperCase)).getId() : str;
        byte[] sharedSecretBytes = getSharedSecretBytes(calcSecret(), id, getKeySize(id));
        String algorithm = getAlgorithm(str);
        if (des.containsKey(algorithm)) {
            DESParameters.setOddParity(sharedSecretBytes);
        }
        return new SecretKeySpec(sharedSecretBytes, algorithm);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.kdf == null) {
            return calcSecret();
        }
        byte[] bArrCalcSecret = calcSecret();
        try {
            return getSharedSecretBytes(bArrCalcSecret, null, bArrCalcSecret.length * 8);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
