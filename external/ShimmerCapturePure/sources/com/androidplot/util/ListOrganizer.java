package com.androidplot.util;

import java.util.List;

/* loaded from: classes.dex */
public class ListOrganizer<ElementType> implements ZIndexable<ElementType> {
    private List<ElementType> a;

    public ListOrganizer(List<ElementType> list) {
        this.a = list;
    }

    @Override // com.androidplot.util.ZIndexable
    public List<ElementType> elements() {
        return this.a;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveToTop(ElementType elementtype) {
        if (!this.a.remove(elementtype)) {
            return false;
        }
        List<ElementType> list = this.a;
        list.add(list.size(), elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveAbove(ElementType elementtype, ElementType elementtype2) {
        if (elementtype == elementtype2) {
            throw new IllegalArgumentException("Illegal argument to moveAbove(A, B); A cannot be equal to B.");
        }
        this.a.remove(elementtype);
        this.a.add(this.a.indexOf(elementtype2) + 1, elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveBeneath(ElementType elementtype, ElementType elementtype2) {
        if (elementtype == elementtype2) {
            throw new IllegalArgumentException("Illegal argument to moveBeaneath(A, B); A cannot be equal to B.");
        }
        this.a.remove(elementtype);
        this.a.add(this.a.indexOf(elementtype2), elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveToBottom(ElementType elementtype) {
        this.a.remove(elementtype);
        this.a.add(0, elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveUp(ElementType elementtype) {
        int iIndexOf = this.a.indexOf(elementtype);
        if (iIndexOf == -1) {
            return false;
        }
        if (iIndexOf >= this.a.size() - 1) {
            return true;
        }
        return moveAbove(elementtype, this.a.get(iIndexOf + 1));
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveDown(ElementType elementtype) {
        int iIndexOf = this.a.indexOf(elementtype);
        if (iIndexOf == -1) {
            return false;
        }
        if (iIndexOf <= 0) {
            return true;
        }
        return moveBeneath(elementtype, this.a.get(iIndexOf - 1));
    }

    public void addToBottom(ElementType elementtype) {
        this.a.add(0, elementtype);
    }

    public void addToTop(ElementType elementtype) {
        List<ElementType> list = this.a;
        list.add(list.size(), elementtype);
    }
}
