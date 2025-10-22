package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.geometry.Space;

/* loaded from: classes5.dex */
public class NodesSet<S extends Space> implements Iterable<BSPTree<S>> {
    private List<BSPTree<S>> list = new ArrayList();

    public void add(BSPTree<S> bSPTree) {
        Iterator<BSPTree<S>> it2 = this.list.iterator();
        while (it2.hasNext()) {
            if (bSPTree == it2.next()) {
                return;
            }
        }
        this.list.add(bSPTree);
    }

    public void addAll(Iterable<BSPTree<S>> iterable) {
        Iterator<BSPTree<S>> it2 = iterable.iterator();
        while (it2.hasNext()) {
            add(it2.next());
        }
    }

    @Override // java.lang.Iterable
    public Iterator<BSPTree<S>> iterator() {
        return this.list.iterator();
    }
}
