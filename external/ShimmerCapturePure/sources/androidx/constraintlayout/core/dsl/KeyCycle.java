package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* loaded from: classes.dex */
public class KeyCycle extends KeyAttribute {
    private static final String TAG = "KeyCycle";
    private float mWaveOffset;
    private float mWavePeriod;
    private float mWavePhase;
    private Wave mWaveShape;

    KeyCycle(int i, String str) {
        super(i, str);
        this.mWaveShape = null;
        this.mWavePeriod = Float.NaN;
        this.mWaveOffset = Float.NaN;
        this.mWavePhase = Float.NaN;
        this.TYPE = TAG;
    }

    public float getOffset() {
        return this.mWaveOffset;
    }

    public void setOffset(float f) {
        this.mWaveOffset = f;
    }

    public float getPeriod() {
        return this.mWavePeriod;
    }

    public void setPeriod(float f) {
        this.mWavePeriod = f;
    }

    public float getPhase() {
        return this.mWavePhase;
    }

    public void setPhase(float f) {
        this.mWavePhase = f;
    }

    public Wave getShape() {
        return this.mWaveShape;
    }

    public void setShape(Wave wave) {
        this.mWaveShape = wave;
    }

    @Override // androidx.constraintlayout.core.dsl.KeyAttribute
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
