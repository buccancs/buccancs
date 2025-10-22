package org.junit.internal.runners;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

/* loaded from: classes2.dex */
public class ErrorReportingRunner extends Runner {
    private final List<Throwable> causes;
    private final Class<?> testClass;

    public ErrorReportingRunner(Class<?> cls, Throwable th) {
        if (cls == null) {
            throw new NullPointerException("Test class cannot be null");
        }
        this.testClass = cls;
        this.causes = getCauses(th);
    }

    @Override // org.junit.runner.Runner, org.junit.runner.Describable
    public Description getDescription() {
        Description descriptionCreateSuiteDescription = Description.createSuiteDescription(this.testClass);
        Iterator<Throwable> it2 = this.causes.iterator();
        while (it2.hasNext()) {
            descriptionCreateSuiteDescription.addChild(describeCause(it2.next()));
        }
        return descriptionCreateSuiteDescription;
    }

    @Override // org.junit.runner.Runner
    public void run(RunNotifier runNotifier) throws StoppedByUserException {
        Iterator<Throwable> it2 = this.causes.iterator();
        while (it2.hasNext()) {
            runCause(it2.next(), runNotifier);
        }
    }

    private List<Throwable> getCauses(Throwable th) {
        if (th instanceof InvocationTargetException) {
            return getCauses(th.getCause());
        }
        if (th instanceof org.junit.runners.model.InitializationError) {
            return ((org.junit.runners.model.InitializationError) th).getCauses();
        }
        if (th instanceof InitializationError) {
            return ((InitializationError) th).getCauses();
        }
        return Arrays.asList(th);
    }

    private Description describeCause(Throwable th) {
        return Description.createTestDescription(this.testClass, "initializationError");
    }

    private void runCause(Throwable th, RunNotifier runNotifier) throws StoppedByUserException {
        Description descriptionDescribeCause = describeCause(th);
        runNotifier.fireTestStarted(descriptionDescribeCause);
        runNotifier.fireTestFailure(new Failure(descriptionDescribeCause, th));
        runNotifier.fireTestFinished(descriptionDescribeCause);
    }
}
