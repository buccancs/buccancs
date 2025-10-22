package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class KeyCycles extends KeyAttributes {
    private float[] mWaveOffset;
    private float[] mWavePeriod;
    private float[] mWavePhase;
    private Wave mWaveShape;

    KeyCycles(int i, String... strArr) {
        super(i, strArr);
        this.mWaveShape = null;
        this.mWavePeriod = null;
        this.mWaveOffset = null;
        this.mWavePhase = null;
        this.TYPE = "KeyCycle";
    }

    public float[] getWaveOffset() {
        return this.mWaveOffset;
    }

    public void setWaveOffset(float... fArr) {
        this.mWaveOffset = fArr;
    }

    public float[] getWavePeriod() {
        return this.mWavePeriod;
    }

    public void setWavePeriod(float... fArr) {
        this.mWavePeriod = fArr;
    }

    public float[] getWavePhase() {
        return this.mWavePhase;
    }

    public void setWavePhase(float... fArr) {
        this.mWavePhase = fArr;
    }

    public Wave getWaveShape() {
        return this.mWaveShape;
    }

    public void setWaveShape(Wave wave) {
        this.mWaveShape = wave;
    }

    @Override // androidx.constraintlayout.core.dsl.KeyAttributes
    protected void attributesToString(StringBuilder sb) {
        super.attributesToString(sb);
        if (this.mWaveShape != null) {
            sb.append("shape:'");
            sb.append(this.mWaveShape);
            sb.append("',\n");
        }
        append(sb, TypedValues.CycleType.S_WAVE_PERIOD, this.mWavePeriod);
        append(sb, TypedValues.CycleType.S_WAVE_OFFSET, this.mWaveOffset);
        append(sb, TypedValues.CycleType.S_WAVE_PHASE, this.mWavePhase);
    }

    public enum Wave {
        SIN,
        SQUARE,
        TRIANGLE,
        SAW,
        REVERSE_SAW,
        COS
    }
}
