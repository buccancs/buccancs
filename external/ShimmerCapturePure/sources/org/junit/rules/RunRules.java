package org.junit.rules;

import java.util.Iterator;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes2.dex */
public class RunRules extends Statement {
    private final Statement statement;

    public RunRules(Statement statement, Iterable<TestRule> iterable, Description description) {
        this.statement = applyAll(statement, iterable, description);
    }

    private static Statement applyAll(Statement statement, Iterable<TestRule> iterable, Description description) {
        Iterator<TestRule> it2 = iterable.iterator();
        while (it2.hasNext()) {
            statement = it2.next().apply(statement, description);
        }
        return statement;
    }

    @Override // org.junit.runners.model.Statement
    public void evaluate() throws Throwable {
        this.statement.evaluate();
    }
}
