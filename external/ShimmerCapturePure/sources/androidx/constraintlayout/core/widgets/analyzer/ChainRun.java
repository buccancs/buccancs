package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    ArrayList<WidgetRun> mWidgets;
    private int mChainStyle;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.mWidgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it2 = this.mWidgets.iterator();
        while (it2.hasNext()) {
            WidgetRun next = it2.next();
            sb.append("<");
            sb.append(next);
            sb.append("> ");
        }
        return sb.toString();
    }

    @Override
        // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        int size = this.mWidgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.mWidgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.mWidgets.size();
        long wrapDimension = 0;
        for (int i = 0; i < size; i++) {
            wrapDimension = wrapDimension + r4.start.mMargin + this.mWidgets.get(i).getWrapDimension() + r4.end.mMargin;
        }
        return wrapDimension;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.mWidget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
            }
        }
        this.mWidget = constraintWidget;
        this.mWidgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.mWidgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it2 = this.mWidgets.iterator();
        while (it2.hasNext()) {
            WidgetRun next = it2.next();
            if (this.orientation == 0) {
                next.mWidget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.mWidget.verticalChainRun = this;
            }
        }
        if (this.orientation == 0 && ((ConstraintWidgetContainer) this.mWidget.getParent()).isRtl() && this.mWidgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.mWidgets;
            this.mWidget = arrayList.get(arrayList.size() - 1).mWidget;
        }
        this.mChainStyle = this.orientation == 0 ? this.mWidget.getHorizontalChainStyle() : this.mWidget.getVerticalChainStyle();
    }

    @Override
        // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.mRunGroup = null;
        Iterator<WidgetRun> it2 = this.mWidgets.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
    }

    @Override
        // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override
    // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f;
        int i6;
        int i7;
        int i8;
        int i9;
        float f2;
        int i10;
        int i11;
        int i12;
        if (this.start.resolved && this.end.resolved) {
            ConstraintWidget parent = this.mWidget.getParent();
            boolean zIsRtl = parent instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer) parent).isRtl() : false;
            int i13 = this.end.value - this.start.value;
            int size = this.mWidgets.size();
            int i14 = 0;
            while (true) {
                i = -1;
                i2 = 8;
                if (i14 >= size) {
                    i14 = -1;
                    break;
                } else if (this.mWidgets.get(i14).mWidget.getVisibility() != 8) {
                    break;
                } else {
                    i14++;
                }
            }
            int i15 = size - 1;
            int i16 = i15;
            while (true) {
                if (i16 < 0) {
                    break;
                }
                if (this.mWidgets.get(i16).mWidget.getVisibility() != 8) {
                    i = i16;
                    break;
                }
                i16--;
            }
            int i17 = 0;
            while (i17 < 2) {
                int i18 = 0;
                i4 = 0;
                i5 = 0;
                int i19 = 0;
                f = 0.0f;
                while (i18 < size) {
                    WidgetRun widgetRun = this.mWidgets.get(i18);
                    if (widgetRun.mWidget.getVisibility() != i2) {
                        i19++;
                        if (i18 > 0 && i18 >= i14) {
                            i4 += widgetRun.start.mMargin;
                        }
                        int i20 = widgetRun.mDimension.value;
                        boolean z = widgetRun.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (z) {
                            if (this.orientation == 0 && !widgetRun.mWidget.mHorizontalRun.mDimension.resolved) {
                                return;
                            }
                            if (this.orientation == 1 && !widgetRun.mWidget.mVerticalRun.mDimension.resolved) {
                                return;
                            }
                        } else {
                            if (widgetRun.matchConstraintsType == 1 && i17 == 0) {
                                i20 = widgetRun.mDimension.wrapValue;
                                i5++;
                            } else if (widgetRun.mDimension.resolved) {
                            }
                            z = true;
                        }
                        if (z) {
                            i4 += i20;
                        } else {
                            i5++;
                            float f3 = widgetRun.mWidget.mWeight[this.orientation];
                            if (f3 >= 0.0f) {
                                f += f3;
                            }
                        }
                        if (i18 < i15 && i18 < i) {
                            i4 += -widgetRun.end.mMargin;
                        }
                    }
                    i18++;
                    i2 = 8;
                }
                if (i4 < i13 || i5 == 0) {
                    i3 = i19;
                    break;
                } else {
                    i17++;
                    i2 = 8;
                }
            }
            i3 = 0;
            i4 = 0;
            i5 = 0;
            f = 0.0f;
            int i21 = this.start.value;
            if (zIsRtl) {
                i21 = this.end.value;
            }
            if (i4 > i13) {
                i21 = zIsRtl ? i21 + ((int) (((i4 - i13) / 2.0f) + 0.5f)) : i21 - ((int) (((i4 - i13) / 2.0f) + 0.5f));
            }
            if (i5 > 0) {
                float f4 = i13 - i4;
                int i22 = (int) ((f4 / i5) + 0.5f);
                int i23 = 0;
                int i24 = 0;
                while (i23 < size) {
                    WidgetRun widgetRun2 = this.mWidgets.get(i23);
                    int i25 = i22;
                    if (widgetRun2.mWidget.getVisibility() == 8 || widgetRun2.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widgetRun2.mDimension.resolved) {
                        i9 = i21;
                        f2 = f4;
                        i10 = i4;
                    } else {
                        int i26 = f > 0.0f ? (int) (((widgetRun2.mWidget.mWeight[this.orientation] * f4) / f) + 0.5f) : i25;
                        if (this.orientation == 0) {
                            i11 = widgetRun2.mWidget.mMatchConstraintMaxWidth;
                            f2 = f4;
                            i12 = widgetRun2.mWidget.mMatchConstraintMinWidth;
                        } else {
                            f2 = f4;
                            i11 = widgetRun2.mWidget.mMatchConstraintMaxHeight;
                            i12 = widgetRun2.mWidget.mMatchConstraintMinHeight;
                        }
                        i10 = i4;
                        i9 = i21;
                        int iMax = Math.max(i12, widgetRun2.matchConstraintsType == 1 ? Math.min(i26, widgetRun2.mDimension.wrapValue) : i26);
                        if (i11 > 0) {
                            iMax = Math.min(i11, iMax);
                        }
                        if (iMax != i26) {
                            i24++;
                            i26 = iMax;
                        }
                        widgetRun2.mDimension.resolve(i26);
                    }
                    i23++;
                    i22 = i25;
                    f4 = f2;
                    i4 = i10;
                    i21 = i9;
                }
                i6 = i21;
                int i27 = i4;
                if (i24 > 0) {
                    i5 -= i24;
                    int i28 = 0;
                    for (int i29 = 0; i29 < size; i29++) {
                        WidgetRun widgetRun3 = this.mWidgets.get(i29);
                        if (widgetRun3.mWidget.getVisibility() != 8) {
                            if (i29 > 0 && i29 >= i14) {
                                i28 += widgetRun3.start.mMargin;
                            }
                            i28 += widgetRun3.mDimension.value;
                            if (i29 < i15 && i29 < i) {
                                i28 += -widgetRun3.end.mMargin;
                            }
                        }
                    }
                    i4 = i28;
                } else {
                    i4 = i27;
                }
                i7 = 2;
                if (this.mChainStyle == 2 && i24 == 0) {
                    this.mChainStyle = 0;
                }
            } else {
                i6 = i21;
                i7 = 2;
            }
            if (i4 > i13) {
                this.mChainStyle = i7;
            }
            if (i3 > 0 && i5 == 0 && i14 == i) {
                this.mChainStyle = i7;
            }
            int i30 = this.mChainStyle;
            if (i30 == 1) {
                if (i3 > 1) {
                    i8 = (i13 - i4) / (i3 - 1);
                } else {
                    i8 = i3 == 1 ? (i13 - i4) / 2 : 0;
                }
                if (i5 > 0) {
                    i8 = 0;
                }
                int i31 = i6;
                for (int i32 = 0; i32 < size; i32++) {
                    WidgetRun widgetRun4 = this.mWidgets.get(zIsRtl ? size - (i32 + 1) : i32);
                    if (widgetRun4.mWidget.getVisibility() == 8) {
                        widgetRun4.start.resolve(i31);
                        widgetRun4.end.resolve(i31);
                    } else {
                        if (i32 > 0) {
                            i31 = zIsRtl ? i31 - i8 : i31 + i8;
                        }
                        if (i32 > 0 && i32 >= i14) {
                            if (zIsRtl) {
                                i31 -= widgetRun4.start.mMargin;
                            } else {
                                i31 += widgetRun4.start.mMargin;
                            }
                        }
                        if (zIsRtl) {
                            widgetRun4.end.resolve(i31);
                        } else {
                            widgetRun4.start.resolve(i31);
                        }
                        int i33 = widgetRun4.mDimension.value;
                        if (widgetRun4.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun4.matchConstraintsType == 1) {
                            i33 = widgetRun4.mDimension.wrapValue;
                        }
                        i31 = zIsRtl ? i31 - i33 : i31 + i33;
                        if (zIsRtl) {
                            widgetRun4.start.resolve(i31);
                        } else {
                            widgetRun4.end.resolve(i31);
                        }
                        widgetRun4.mResolved = true;
                        if (i32 < i15 && i32 < i) {
                            if (zIsRtl) {
                                i31 -= -widgetRun4.end.mMargin;
                            } else {
                                i31 += -widgetRun4.end.mMargin;
                            }
                        }
                    }
                }
                return;
            }
            if (i30 == 0) {
                int i34 = (i13 - i4) / (i3 + 1);
                if (i5 > 0) {
                    i34 = 0;
                }
                int i35 = i6;
                for (int i36 = 0; i36 < size; i36++) {
                    WidgetRun widgetRun5 = this.mWidgets.get(zIsRtl ? size - (i36 + 1) : i36);
                    if (widgetRun5.mWidget.getVisibility() == 8) {
                        widgetRun5.start.resolve(i35);
                        widgetRun5.end.resolve(i35);
                    } else {
                        int i37 = zIsRtl ? i35 - i34 : i35 + i34;
                        if (i36 > 0 && i36 >= i14) {
                            if (zIsRtl) {
                                i37 -= widgetRun5.start.mMargin;
                            } else {
                                i37 += widgetRun5.start.mMargin;
                            }
                        }
                        if (zIsRtl) {
                            widgetRun5.end.resolve(i37);
                        } else {
                            widgetRun5.start.resolve(i37);
                        }
                        int iMin = widgetRun5.mDimension.value;
                        if (widgetRun5.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun5.matchConstraintsType == 1) {
                            iMin = Math.min(iMin, widgetRun5.mDimension.wrapValue);
                        }
                        i35 = zIsRtl ? i37 - iMin : i37 + iMin;
                        if (zIsRtl) {
                            widgetRun5.start.resolve(i35);
                        } else {
                            widgetRun5.end.resolve(i35);
                        }
                        if (i36 < i15 && i36 < i) {
                            if (zIsRtl) {
                                i35 -= -widgetRun5.end.mMargin;
                            } else {
                                i35 += -widgetRun5.end.mMargin;
                            }
                        }
                    }
                }
                return;
            }
            if (i30 == 2) {
                float horizontalBiasPercent = this.orientation == 0 ? this.mWidget.getHorizontalBiasPercent() : this.mWidget.getVerticalBiasPercent();
                if (zIsRtl) {
                    horizontalBiasPercent = 1.0f - horizontalBiasPercent;
                }
                int i38 = (int) (((i13 - i4) * horizontalBiasPercent) + 0.5f);
                if (i38 < 0 || i5 > 0) {
                    i38 = 0;
                }
                int i39 = zIsRtl ? i6 - i38 : i6 + i38;
                for (int i40 = 0; i40 < size; i40++) {
                    WidgetRun widgetRun6 = this.mWidgets.get(zIsRtl ? size - (i40 + 1) : i40);
                    if (widgetRun6.mWidget.getVisibility() == 8) {
                        widgetRun6.start.resolve(i39);
                        widgetRun6.end.resolve(i39);
                    } else {
                        if (i40 > 0 && i40 >= i14) {
                            if (zIsRtl) {
                                i39 -= widgetRun6.start.mMargin;
                            } else {
                                i39 += widgetRun6.start.mMargin;
                            }
                        }
                        if (zIsRtl) {
                            widgetRun6.end.resolve(i39);
                        } else {
                            widgetRun6.start.resolve(i39);
                        }
                        int i41 = widgetRun6.mDimension.value;
                        if (widgetRun6.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun6.matchConstraintsType == 1) {
                            i41 = widgetRun6.mDimension.wrapValue;
                        }
                        i39 = zIsRtl ? i39 - i41 : i39 + i41;
                        if (zIsRtl) {
                            widgetRun6.start.resolve(i39);
                        } else {
                            widgetRun6.end.resolve(i39);
                        }
                        if (i40 < i15 && i40 < i) {
                            if (zIsRtl) {
                                i39 -= -widgetRun6.end.mMargin;
                            } else {
                                i39 += -widgetRun6.end.mMargin;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.mWidgets.size(); i++) {
            this.mWidgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.mWidgets.size(); i++) {
            WidgetRun widgetRun = this.mWidgets.get(i);
            if (widgetRun.mWidget.getVisibility() != 8) {
                return widgetRun.mWidget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.mWidgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.mWidgets.get(size);
            if (widgetRun.mWidget.getVisibility() != 8) {
                return widgetRun.mWidget;
            }
        }
        return null;
    }

    @Override
        // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Iterator<WidgetRun> it2 = this.mWidgets.iterator();
        while (it2.hasNext()) {
            it2.next().apply();
        }
        int size = this.mWidgets.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.mWidgets.get(0).mWidget;
        ConstraintWidget constraintWidget2 = this.mWidgets.get(size - 1).mWidget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }
}
