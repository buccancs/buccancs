package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintSet {
    private final String mName;
    ArrayList<Constraint> mConstraints = new ArrayList<>();
    ArrayList<Helper> mHelpers = new ArrayList<>();

    public ConstraintSet(String str) {
        this.mName = str;
    }

    public void add(Constraint constraint) {
        this.mConstraints.add(constraint);
    }

    public void add(Helper helper) {
        this.mHelpers.add(helper);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.mName + ":{\n");
        if (!this.mConstraints.isEmpty()) {
            Iterator<Constraint> it2 = this.mConstraints.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().toString());
            }
        }
        if (!this.mHelpers.isEmpty()) {
            Iterator<Helper> it3 = this.mHelpers.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().toString());
            }
        }
        sb.append("},\n");
        return sb.toString();
    }
}
