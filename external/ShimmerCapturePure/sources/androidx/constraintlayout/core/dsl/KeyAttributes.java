package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

import java.util.Arrays;

/* loaded from: classes.dex */
public class KeyAttributes extends Keys {
    protected String TYPE = "KeyAttributes";
    private int[] mFrames;
    private String[] mTarget;
    private String mTransitionEasing;
    private Fit mCurveFit = null;
    private Visibility[] mVisibility = null;
    private float[] mAlpha = null;
    private float[] mRotation = null;
    private float[] mRotationX = null;
    private float[] mRotationY = null;
    private float[] mPivotX = null;
    private float[] mPivotY = null;
    private float[] mTransitionPathRotate = null;
    private float[] mScaleX = null;
    private float[] mScaleY = null;
    private float[] mTranslationX = null;
    private float[] mTranslationY = null;
    private float[] mTranslationZ = null;

    KeyAttributes(int i, String... strArr) {
        this.mFrames = null;
        this.mTarget = strArr;
        this.mFrames = new int[i];
        float length = 100.0f / (r3.length + 1);
        int i2 = 0;
        while (true) {
            int[] iArr = this.mFrames;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = (int) ((i2 * length) + length);
            i2++;
        }
    }

    public float[] getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(float... fArr) {
        this.mAlpha = fArr;
    }

    public Fit getCurveFit() {
        return this.mCurveFit;
    }

    public void setCurveFit(Fit fit) {
        this.mCurveFit = fit;
    }

    public float[] getPivotX() {
        return this.mPivotX;
    }

    public void setPivotX(float... fArr) {
        this.mPivotX = fArr;
    }

    public float[] getPivotY() {
        return this.mPivotY;
    }

    public void setPivotY(float... fArr) {
        this.mPivotY = fArr;
    }

    public float[] getRotation() {
        return this.mRotation;
    }

    public void setRotation(float... fArr) {
        this.mRotation = fArr;
    }

    public float[] getRotationX() {
        return this.mRotationX;
    }

    public void setRotationX(float... fArr) {
        this.mRotationX = fArr;
    }

    public float[] getRotationY() {
        return this.mRotationY;
    }

    public void setRotationY(float... fArr) {
        this.mRotationY = fArr;
    }

    public float[] getScaleX() {
        return this.mScaleX;
    }

    public void setScaleX(float[] fArr) {
        this.mScaleX = fArr;
    }

    public float[] getScaleY() {
        return this.mScaleY;
    }

    public void setScaleY(float[] fArr) {
        this.mScaleY = fArr;
    }

    public String[] getTarget() {
        return this.mTarget;
    }

    public void setTarget(String[] strArr) {
        this.mTarget = strArr;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public void setTransitionEasing(String str) {
        this.mTransitionEasing = str;
    }

    public float[] getTransitionPathRotate() {
        return this.mTransitionPathRotate;
    }

    public void setTransitionPathRotate(float... fArr) {
        this.mTransitionPathRotate = fArr;
    }

    public float[] getTranslationX() {
        return this.mTranslationX;
    }

    public void setTranslationX(float[] fArr) {
        this.mTranslationX = fArr;
    }

    public float[] getTranslationY() {
        return this.mTranslationY;
    }

    public void setTranslationY(float[] fArr) {
        this.mTranslationY = fArr;
    }

    public float[] getTranslationZ() {
        return this.mTranslationZ;
    }

    public void setTranslationZ(float[] fArr) {
        this.mTranslationZ = fArr;
    }

    public Visibility[] getVisibility() {
        return this.mVisibility;
    }

    public void setVisibility(Visibility... visibilityArr) {
        this.mVisibility = visibilityArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.TYPE);
        sb.append(":{\n");
        attributesToString(sb);
        sb.append("},\n");
        return sb.toString();
    }

    protected void attributesToString(StringBuilder sb) {
        append(sb, TypedValues.AttributesType.S_TARGET, this.mTarget);
        sb.append("frame:");
        sb.append(Arrays.toString(this.mFrames));
        sb.append(",\n");
        append(sb, "easing", this.mTransitionEasing);
        if (this.mCurveFit != null) {
            sb.append("fit:'");
            sb.append(this.mCurveFit);
            sb.append("',\n");
        }
        if (this.mVisibility != null) {
            sb.append("visibility:'");
            sb.append(Arrays.toString(this.mVisibility));
            sb.append("',\n");
        }
        append(sb, "alpha", this.mAlpha);
        append(sb, "rotationX", this.mRotationX);
        append(sb, "rotationY", this.mRotationY);
        append(sb, "rotationZ", this.mRotation);
        append(sb, "pivotX", this.mPivotX);
        append(sb, "pivotY", this.mPivotY);
        append(sb, "pathRotate", this.mTransitionPathRotate);
        append(sb, "scaleX", this.mScaleX);
        append(sb, "scaleY", this.mScaleY);
        append(sb, "translationX", this.mTranslationX);
        append(sb, "translationY", this.mTranslationY);
        append(sb, "translationZ", this.mTranslationZ);
    }

    public enum Fit {
        SPLINE,
        LINEAR
    }

    public enum Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }
}
