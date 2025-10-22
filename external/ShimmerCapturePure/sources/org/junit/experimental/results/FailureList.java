package org.junit.experimental.results;

import java.util.Iterator;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes2.dex */
class FailureList {
    private final List<Failure> failures;

    public FailureList(List<Failure> list) {
        this.failures = list;
    }

    public Result result() {
        Result result = new Result();
        RunListener runListenerCreateListener = result.createListener();
        Iterator<Failure> it2 = this.failures.iterator();
        while (it2.hasNext()) {
            try {
                runListenerCreateListener.testFailure(it2.next());
            } catch (Exception unused) {
                throw new RuntimeException("I can't believe this happened");
            }
        }
        return result;
    }
}
