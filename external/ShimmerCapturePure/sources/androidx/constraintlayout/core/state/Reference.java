package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public interface Reference {
    void apply();

    ConstraintWidget getConstraintWidget();

    void setConstraintWidget(ConstraintWidget constraintWidget);

    Facade getFacade();

    Object getKey();

    void setKey(Object obj);
}
