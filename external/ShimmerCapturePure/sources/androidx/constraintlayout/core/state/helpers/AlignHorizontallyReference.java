package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;

import java.util.Iterator;

/* loaded from: classes.dex */
public class AlignHorizontallyReference extends HelperReference {
    private float mBias;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    @Override
    // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        Iterator<Object> it2 = this.mReferences.iterator();
        while (it2.hasNext()) {
            ConstraintReference constraintReferenceConstraints = this.mHelperState.constraints(it2.next());
            constraintReferenceConstraints.clearHorizontal();
            if (this.mStartToStart != null) {
                constraintReferenceConstraints.startToStart(this.mStartToStart);
            } else if (this.mStartToEnd != null) {
                constraintReferenceConstraints.startToEnd(this.mStartToEnd);
            } else {
                constraintReferenceConstraints.startToStart(State.PARENT);
            }
            if (this.mEndToStart != null) {
                constraintReferenceConstraints.endToStart(this.mEndToStart);
            } else if (this.mEndToEnd != null) {
                constraintReferenceConstraints.endToEnd(this.mEndToEnd);
            } else {
                constraintReferenceConstraints.endToEnd(State.PARENT);
            }
            float f = this.mBias;
            if (f != 0.5f) {
                constraintReferenceConstraints.horizontalBias(f);
            }
        }
    }
}
