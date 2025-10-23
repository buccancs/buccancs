package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class OnSwipe {
    public static final int FLAG_DISABLE_POST_SCROLL = 1;
    public static final int FLAG_DISABLE_SCROLL = 2;
    private Mode mAutoCompleteMode;
    private Drag mDragDirection;
    private float mDragScale;
    private float mDragThreshold;
    private String mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private TouchUp mOnTouchUp;
    private String mRotationCenterId;
    private Boundary mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private String mTouchAnchorId;
    private Side mTouchAnchorSide;

    public OnSwipe() {
        this.mDragDirection = null;
        this.mTouchAnchorSide = null;
        this.mTouchAnchorId = null;
        this.mLimitBoundsTo = null;
        this.mOnTouchUp = null;
        this.mRotationCenterId = null;
        this.mMaxVelocity = Float.NaN;
        this.mMaxAcceleration = Float.NaN;
        this.mDragScale = Float.NaN;
        this.mDragThreshold = Float.NaN;
        this.mSpringDamping = Float.NaN;
        this.mSpringMass = Float.NaN;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = null;
        this.mAutoCompleteMode = null;
    }

    public OnSwipe(String str, Side side, Drag drag) {
        this.mLimitBoundsTo = null;
        this.mOnTouchUp = null;
        this.mRotationCenterId = null;
        this.mMaxVelocity = Float.NaN;
        this.mMaxAcceleration = Float.NaN;
        this.mDragScale = Float.NaN;
        this.mDragThreshold = Float.NaN;
        this.mSpringDamping = Float.NaN;
        this.mSpringMass = Float.NaN;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = null;
        this.mAutoCompleteMode = null;
        this.mTouchAnchorId = str;
        this.mTouchAnchorSide = side;
        this.mDragDirection = drag;
    }

    public Mode getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public void setAutoCompleteMode(Mode mode) {
        this.mAutoCompleteMode = mode;
    }

    public Drag getDragDirection() {
        return this.mDragDirection;
    }

    public OnSwipe setDragDirection(Drag drag) {
        this.mDragDirection = drag;
        return this;
    }

    public float getDragScale() {
        return this.mDragScale;
    }

    public OnSwipe setDragScale(int i) {
        this.mDragScale = i;
        return this;
    }

    public float getDragThreshold() {
        return this.mDragThreshold;
    }

    public OnSwipe setDragThreshold(int i) {
        this.mDragThreshold = i;
        return this;
    }

    public String getLimitBoundsTo() {
        return this.mLimitBoundsTo;
    }

    public OnSwipe setLimitBoundsTo(String str) {
        this.mLimitBoundsTo = str;
        return this;
    }

    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public OnSwipe setMaxAcceleration(int i) {
        this.mMaxAcceleration = i;
        return this;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public OnSwipe setMaxVelocity(int i) {
        this.mMaxVelocity = i;
        return this;
    }

    public TouchUp getOnTouchUp() {
        return this.mOnTouchUp;
    }

    public OnSwipe setOnTouchUp(TouchUp touchUp) {
        this.mOnTouchUp = touchUp;
        return this;
    }

    public String getRotationCenterId() {
        return this.mRotationCenterId;
    }

    public Boundary getSpringBoundary() {
        return this.mSpringBoundary;
    }

    public OnSwipe setSpringBoundary(Boundary boundary) {
        this.mSpringBoundary = boundary;
        return this;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public OnSwipe setSpringDamping(float f) {
        this.mSpringDamping = f;
        return this;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public OnSwipe setSpringMass(float f) {
        this.mSpringMass = f;
        return this;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public OnSwipe setSpringStiffness(float f) {
        this.mSpringStiffness = f;
        return this;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    public OnSwipe setSpringStopThreshold(float f) {
        this.mSpringStopThreshold = f;
        return this;
    }

    public String getTouchAnchorId() {
        return this.mTouchAnchorId;
    }

    public OnSwipe setTouchAnchorId(String str) {
        this.mTouchAnchorId = str;
        return this;
    }

    public Side getTouchAnchorSide() {
        return this.mTouchAnchorSide;
    }

    public OnSwipe setTouchAnchorSide(Side side) {
        this.mTouchAnchorSide = side;
        return this;
    }

    public OnSwipe setRotateCenter(String str) {
        this.mRotationCenterId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OnSwipe:{\n");
        if (this.mTouchAnchorId != null) {
            sb.append("anchor:'");
            sb.append(this.mTouchAnchorId);
            sb.append("',\n");
        }
        if (this.mDragDirection != null) {
            sb.append("direction:'");
            sb.append(this.mDragDirection.toString().toLowerCase());
            sb.append("',\n");
        }
        if (this.mTouchAnchorSide != null) {
            sb.append("side:'");
            sb.append(this.mTouchAnchorSide.toString().toLowerCase());
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mDragScale)) {
            sb.append("scale:'");
            sb.append(this.mDragScale);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mDragThreshold)) {
            sb.append("threshold:'");
            sb.append(this.mDragThreshold);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mMaxVelocity)) {
            sb.append("maxVelocity:'");
            sb.append(this.mMaxVelocity);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mMaxAcceleration)) {
            sb.append("maxAccel:'");
            sb.append(this.mMaxAcceleration);
            sb.append("',\n");
        }
        if (this.mLimitBoundsTo != null) {
            sb.append("limitBounds:'");
            sb.append(this.mLimitBoundsTo);
            sb.append("',\n");
        }
        if (this.mAutoCompleteMode != null) {
            sb.append("mode:'");
            sb.append(this.mAutoCompleteMode.toString().toLowerCase());
            sb.append("',\n");
        }
        if (this.mOnTouchUp != null) {
            sb.append("touchUp:'");
            sb.append(this.mOnTouchUp.toString().toLowerCase());
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mSpringMass)) {
            sb.append("springMass:'");
            sb.append(this.mSpringMass);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mSpringStiffness)) {
            sb.append("springStiffness:'");
            sb.append(this.mSpringStiffness);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mSpringDamping)) {
            sb.append("springDamping:'");
            sb.append(this.mSpringDamping);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.mSpringStopThreshold)) {
            sb.append("stopThreshold:'");
            sb.append(this.mSpringStopThreshold);
            sb.append("',\n");
        }
        if (this.mSpringBoundary != null) {
            sb.append("springBoundary:'");
            sb.append(this.mSpringBoundary);
            sb.append("',\n");
        }
        if (this.mRotationCenterId != null) {
            sb.append("around:'");
            sb.append(this.mRotationCenterId);
            sb.append("',\n");
        }
        sb.append("},\n");
        return sb.toString();
    }

    public enum Boundary {
        OVERSHOOT,
        BOUNCE_START,
        BOUNCE_END,
        BOUNCE_BOTH
    }

    public enum Drag {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        START,
        END,
        CLOCKWISE,
        ANTICLOCKWISE
    }

    public enum Mode {
        VELOCITY,
        SPRING
    }

    public enum Side {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM,
        MIDDLE,
        START,
        END
    }

    public enum TouchUp {
        AUTOCOMPLETE,
        TO_START,
        NEVER_COMPLETE_END,
        TO_END,
        STOP,
        DECELERATE,
        DECELERATE_COMPLETE,
        NEVER_COMPLETE_START
    }
}
