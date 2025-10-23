package org.junit.experimental.categories;

import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.util.ArrayList;
import java.util.List;

import org.junit.internal.Classes;
import org.junit.runner.FilterFactory;
import org.junit.runner.FilterFactoryParams;
import org.junit.runner.manipulation.Filter;

/* loaded from: classes2.dex */
abstract class CategoryFilterFactory implements FilterFactory {
    CategoryFilterFactory() {
    }

    protected abstract Filter createFilter(List<Class<?>> list);

    @Override // org.junit.runner.FilterFactory
    public Filter createFilter(FilterFactoryParams filterFactoryParams) throws FilterFactory.FilterNotCreatedException {
        try {
            return createFilter(parseCategories(filterFactoryParams.getArgs()));
        } catch (ClassNotFoundException e) {
            throw new FilterFactory.FilterNotCreatedException(e);
        }
    }

    private List<Class<?>> parseCategories(String str) throws ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(UtilVerisenseDriver.CSV_DELIMITER)) {
            arrayList.add(Classes.getClass(str2));
        }
        return arrayList;
    }
}
