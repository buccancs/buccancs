package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.Utils;
import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    public static final boolean DEBUG = false;
    public static final String TAG = "MotionPaths";
    static final int CARTESIAN = 2;
    static final int PERPENDICULAR = 1;
    static String[] sNames = {"position", "x", "y", "width", "height", "pathRotate"};
    public float rotationY = 0.0f;
    int mVisibility;
    int mVisibilityMode = 0;
    LinkedHashMap<String, CustomVariable> mCustomVariable = new LinkedHashMap<>();
    int mMode = 0;
    double[] mTempValue = new double[18];
    double[] mTempDelta = new double[18];
    private float mHeight;
    private Easing mKeyFrameEasing;
    private float mPosition;
    private float mWidth;
    private float mX;
    private float mY;
    private float mAlpha = 1.0f;
    private boolean mApplyElevation = false;
    private float mElevation = 0.0f;
    private float mRotation = 0.0f;
    private float mRotationX = 0.0f;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTranslationX = 0.0f;
    private float mTranslationY = 0.0f;
    private float mTranslationZ = 0.0f;
    private int mDrawPath = 0;
    private float mPathRotate = Float.NaN;
    private float mProgress = Float.NaN;
    private int mAnimateRelativeTo = -1;

    MotionConstrainedPoint() {
    }

    void setBounds(float f, float f2, float f3, float f4) {
        this.mX = f;
        this.mY = f2;
        this.mWidth = f3;
        this.mHeight = f4;
    }

    private boolean diff(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        if (diff(this.mAlpha, motionConstrainedPoint.mAlpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.mElevation, motionConstrainedPoint.mElevation)) {
            hashSet.add("translationZ");
        }
        int i = this.mVisibility;
        int i2 = motionConstrainedPoint.mVisibility;
        if (i != i2 && this.mVisibilityMode == 0 && (i == 4 || i2 == 4)) {
            hashSet.add("alpha");
        }
        if (diff(this.mRotation, motionConstrainedPoint.mRotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add("progress");
        }
        if (diff(this.mRotationX, motionConstrainedPoint.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add("rotationY");
        }
        if (diff(this.mPivotX, motionConstrainedPoint.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add("pivotY");
        }
        if (diff(this.mScaleX, motionConstrainedPoint.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (diff(this.mScaleY, motionConstrainedPoint.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (diff(this.mTranslationX, motionConstrainedPoint.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (diff(this.mTranslationY, motionConstrainedPoint.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (diff(this.mTranslationZ, motionConstrainedPoint.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (diff(this.mElevation, motionConstrainedPoint.mElevation)) {
            hashSet.add("elevation");
        }
    }

    void different(MotionConstrainedPoint motionConstrainedPoint, boolean[] zArr, String[] strArr) {
        zArr[0] = zArr[0] | diff(this.mPosition, motionConstrainedPoint.mPosition);
        zArr[1] = zArr[1] | diff(this.mX, motionConstrainedPoint.mX);
        zArr[2] = zArr[2] | diff(this.mY, motionConstrainedPoint.mY);
        zArr[3] = zArr[3] | diff(this.mWidth, motionConstrainedPoint.mWidth);
        zArr[4] = diff(this.mHeight, motionConstrainedPoint.mHeight) | zArr[4];
    }

    void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.mPosition, this.mX, this.mY, this.mWidth, this.mHeight, this.mAlpha, this.mElevation, this.mRotation, this.mRotationX, this.rotationY, this.mScaleX, this.mScaleY, this.mPivotX, this.mPivotY, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mPathRotate};
        int i = 0;
        for (int i2 : iArr) {
            if (i2 < 18) {
                dArr[i] = fArr[r4];
                i++;
            }
        }
    }

    boolean hasCustomData(String str) {
        return this.mCustomVariable.containsKey(str);
    }

    int getCustomDataCount(String str) {
        return this.mCustomVariable.get(str).numberOfInterpolatedValues();
    }

    int getCustomData(String str, double[] dArr, int i) {
        CustomVariable customVariable = this.mCustomVariable.get(str);
        if (customVariable.numberOfInterpolatedValues() == 1) {
            dArr[i] = customVariable.getValueToInterpolate();
            return 1;
        }
        int iNumberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
        customVariable.getValuesToInterpolate(new float[iNumberOfInterpolatedValues]);
        int i2 = 0;
        while (i2 < iNumberOfInterpolatedValues) {
            dArr[i] = r1[i2];
            i2++;
            i++;
        }
        return iNumberOfInterpolatedValues;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.mPosition, motionConstrainedPoint.mPosition);
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.mVisibility = motionWidget.getVisibility();
        this.mAlpha = motionWidget.getVisibility() != 4 ? 0.0f : motionWidget.getAlpha();
        this.mApplyElevation = false;
        this.mRotation = motionWidget.getRotationZ();
        this.mRotationX = motionWidget.getRotationX();
        this.rotationY = motionWidget.getRotationY();
        this.mScaleX = motionWidget.getScaleX();
        this.mScaleY = motionWidget.getScaleY();
        this.mPivotX = motionWidget.getPivotX();
        this.mPivotY = motionWidget.getPivotY();
        this.mTranslationX = motionWidget.getTranslationX();
        this.mTranslationY = motionWidget.getTranslationY();
        this.mTranslationZ = motionWidget.getTranslationZ();
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.mCustomVariable.put(str, customAttribute);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void addValues(HashMap<String, SplineSet> map, int i) {
        for (String str : map.keySet()) {
            SplineSet splineSet = map.get(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1249320804:
                    if (str.equals("rotationZ")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        c = 6;
                        break;
                    }
                    break;
                case -987906986:
                    if (str.equals("pivotX")) {
                        c = 7;
                        break;
                    }
                    break;
                case -987906985:
                    if (str.equals("pivotY")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        c = 11;
                        break;
                    }
                    break;
                case 803192288:
                    if (str.equals("pathRotate")) {
                        c = '\f';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    splineSet.setPoint(i, Float.isNaN(this.mRotationX) ? 0.0f : this.mRotationX);
                    break;
                case 1:
                    splineSet.setPoint(i, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                    break;
                case 2:
                    splineSet.setPoint(i, Float.isNaN(this.mRotation) ? 0.0f : this.mRotation);
                    break;
                case 3:
                    splineSet.setPoint(i, Float.isNaN(this.mTranslationX) ? 0.0f : this.mTranslationX);
                    break;
                case 4:
                    splineSet.setPoint(i, Float.isNaN(this.mTranslationY) ? 0.0f : this.mTranslationY);
                    break;
                case 5:
                    splineSet.setPoint(i, Float.isNaN(this.mTranslationZ) ? 0.0f : this.mTranslationZ);
                    break;
                case 6:
                    splineSet.setPoint(i, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                    break;
                case 7:
                    splineSet.setPoint(i, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                    break;
                case '\b':
                    splineSet.setPoint(i, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                    break;
                case '\t':
                    splineSet.setPoint(i, Float.isNaN(this.mScaleX) ? 1.0f : this.mScaleX);
                    break;
                case '\n':
                    splineSet.setPoint(i, Float.isNaN(this.mScaleY) ? 1.0f : this.mScaleY);
                    break;
                case 11:
                    splineSet.setPoint(i, Float.isNaN(this.mAlpha) ? 1.0f : this.mAlpha);
                    break;
                case '\f':
                    splineSet.setPoint(i, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Utils.loge("MotionPaths", "UNKNOWN spline " + str);
                        break;
                    } else {
                        String str2 = str.split(UtilVerisenseDriver.CSV_DELIMITER)[1];
                        if (this.mCustomVariable.containsKey(str2)) {
                            CustomVariable customVariable = this.mCustomVariable.get(str2);
                            if (splineSet instanceof SplineSet.CustomSpline) {
                                ((SplineSet.CustomSpline) splineSet).setPoint(i, customVariable);
                                break;
                            } else {
                                Utils.loge("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i + ", value" + customVariable.getValueToInterpolate() + splineSet);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
            }
        }
    }

    public void setState(MotionWidget motionWidget) {
        setBounds(motionWidget.getX(), motionWidget.getY(), motionWidget.getWidth(), motionWidget.getHeight());
        applyParameters(motionWidget);
    }

    public void setState(Rect rect, MotionWidget motionWidget, int i, float f) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(motionWidget);
        this.mPivotX = Float.NaN;
        this.mPivotY = Float.NaN;
        if (i == 1) {
            this.mRotation = f - 90.0f;
        } else {
            if (i != 2) {
                return;
            }
            this.mRotation = f + 90.0f;
        }
    }
}
