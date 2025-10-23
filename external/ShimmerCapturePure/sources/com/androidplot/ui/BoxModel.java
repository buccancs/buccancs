package com.androidplot.ui;

import android.graphics.RectF;

/* loaded from: classes.dex */
public class BoxModel implements BoxModelable {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;

    public BoxModel() {
    }

    public BoxModel(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.e = f5;
        this.f = f6;
        this.g = f7;
        this.h = f8;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginBottom() {
        return this.d;
    }

    public void setMarginBottom(float f) {
        this.d = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginLeft() {
        return this.a;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginLeft(float f) {
        this.a = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginRight() {
        return this.c;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginRight(float f) {
        this.c = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginTop() {
        return this.b;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginTop(float f) {
        this.b = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingBottom() {
        return this.h;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingBottom(float f) {
        this.h = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingLeft() {
        return this.e;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingLeft(float f) {
        this.e = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingRight() {
        return this.g;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingRight(float f) {
        this.g = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingTop() {
        return this.f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingTop(float f) {
        this.f = f;
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getMarginatedRect(RectF rectF) {
        return new RectF(rectF.left + getMarginLeft(), rectF.top + getMarginTop(), rectF.right - getMarginRight(), rectF.bottom - getMarginBottom());
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getPaddedRect(RectF rectF) {
        return new RectF(rectF.left + getPaddingLeft(), rectF.top + getPaddingTop(), rectF.right - getPaddingRight(), rectF.bottom - getPaddingBottom());
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMargins(float f, float f2, float f3, float f4) {
        setMarginLeft(f);
        setMarginTop(f2);
        setMarginRight(f3);
        setMarginBottom(f4);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPadding(float f, float f2, float f3, float f4) {
        setPaddingLeft(f);
        setPaddingTop(f2);
        setPaddingRight(f3);
        setPaddingBottom(f4);
    }
}
