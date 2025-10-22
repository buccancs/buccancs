package org.bouncycastle.asn1.ua;

import com.google.common.base.Ascii;
import com.shimmerresearch.driver.ShimmerObject;
import com.shimmerresearch.sensors.adxl371.SensorADXL371;
import com.shimmerresearch.sensors.lisxmdl.SensorLIS3MDL;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class DSTU4145Params extends ASN1Object {
    private static final byte[] DEFAULT_DKE = {SensorADXL371.SET_ALT_ACCEL_CALIBRATION_COMMAND, -42, -21, 69, -15, 60, ShimmerObject.START_SDBT_COMMAND, ShimmerObject.SET_NSHIMMER_COMMAND, -128, -60, ShimmerObject.TEST_CONNECTION_COMMAND, ShimmerObject.GET_SHIMMERNAME_COMMAND, 35, Ascii.US, ShimmerObject.SET_INTERNAL_EXP_POWER_ENABLE_COMMAND, SensorADXL371.ALT_ACCEL_SAMPLING_RATE_RESPONSE, -10, 88, -21, -92, -64, 55, ShimmerObject.SET_ECG_CALIBRATION_COMMAND, Ascii.GS, 56, -39, ShimmerObject.BAUD_RATE_RESPONSE, -16, ShimmerObject.GET_SHIMMER_VERSION_RESPONSE, -54, 78, 23, -8, -23, ShimmerObject.GET_STATUS_COMMAND, 13, -58, 21, SensorLIS3MDL.GET_ALT_MAG_SAMPLING_RATE_COMMAND, 58, ShimmerObject.GET_EMG_CALIBRATION_COMMAND, ShimmerObject.STOP_SDBT_COMMAND, ShimmerObject.INTERNAL_EXP_POWER_ENABLE_RESPONSE, 11, -63, -34, -93, 100, 56, -75, 100, -22, 44, 23, ShimmerObject.BMP280_CALIBRATION_COEFFICIENTS_RESPONSE, -48, 18, 62, ShimmerObject.SET_DERIVED_CHANNEL_BYTES, -72, -6, -59, ShimmerObject.SET_SHIMMERNAME_COMMAND, 4};
    private byte[] dke;
    private DSTU4145ECBinary ecbinary;
    private ASN1ObjectIdentifier namedCurve;

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
        this.dke = Arrays.clone(bArr);
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.dke = DEFAULT_DKE;
        this.ecbinary = dSTU4145ECBinary;
    }

    public static byte[] getDefaultDKE() {
        return Arrays.clone(DEFAULT_DKE);
    }

    public static DSTU4145Params getInstance(Object obj) {
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj == null) {
            throw new IllegalArgumentException("object parse error");
        }
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
        DSTU4145Params dSTU4145Params = aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier ? new DSTU4145Params(ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0))) : new DSTU4145Params(DSTU4145ECBinary.getInstance(aSN1Sequence.getObjectAt(0)));
        if (aSN1Sequence.size() == 2) {
            byte[] octets = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
            dSTU4145Params.dke = octets;
            if (octets.length != DEFAULT_DKE.length) {
                throw new IllegalArgumentException("object parse error");
            }
        }
        return dSTU4145Params;
    }

    public byte[] getDKE() {
        return Arrays.clone(this.dke);
    }

    public DSTU4145ECBinary getECBinary() {
        return this.ecbinary;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.namedCurve;
    }

    public boolean isNamedCurve() {
        return this.namedCurve != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Encodable aSN1Encodable = this.namedCurve;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.ecbinary;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        if (!Arrays.areEqual(this.dke, DEFAULT_DKE)) {
            aSN1EncodableVector.add(new DEROctetString(this.dke));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
