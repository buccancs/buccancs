package io.opencensus.metrics.export;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.Exemplar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public abstract class Distribution {
    Distribution() {
    }

    public static Distribution create(long j, double d, double d2, BucketOptions bucketOptions, List<Bucket> list) {
        Utils.checkArgument(j >= 0, "count should be non-negative.");
        Utils.checkArgument(d2 >= 0.0d, "sum of squared deviations should be non-negative.");
        if (j == 0) {
            Utils.checkArgument(d == 0.0d, "sum should be 0 if count is 0.");
            Utils.checkArgument(d2 == 0.0d, "sum of squared deviations should be 0 if count is 0.");
        }
        Utils.checkNotNull(bucketOptions, "bucketOptions");
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "buckets")));
        Utils.checkListElementNotNull(listUnmodifiableList, "bucket");
        return new AutoValue_Distribution(j, d, d2, bucketOptions, listUnmodifiableList);
    }

    @Nullable
    public abstract BucketOptions getBucketOptions();

    public abstract List<Bucket> getBuckets();

    public abstract long getCount();

    public abstract double getSum();

    public abstract double getSumOfSquaredDeviations();

    public static abstract class BucketOptions {
        private BucketOptions() {
        }

        public static BucketOptions explicitOptions(List<Double> list) {
            return ExplicitOptions.create(list);
        }

        public abstract <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2);

        public static abstract class ExplicitOptions extends BucketOptions {
            ExplicitOptions() {
                super();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static ExplicitOptions create(List<Double> list) {
                Utils.checkNotNull(list, "bucketBoundaries");
                List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                checkBucketBoundsAreSorted(listUnmodifiableList);
                return new AutoValue_Distribution_BucketOptions_ExplicitOptions(listUnmodifiableList);
            }

            private static void checkBucketBoundsAreSorted(List<Double> list) {
                if (list.size() >= 1) {
                    double dDoubleValue = ((Double) Utils.checkNotNull(list.get(0), "bucketBoundary")).doubleValue();
                    Utils.checkArgument(dDoubleValue > 0.0d, "bucket boundary should be > 0");
                    int i = 1;
                    while (i < list.size()) {
                        double dDoubleValue2 = ((Double) Utils.checkNotNull(list.get(i), "bucketBoundary")).doubleValue();
                        Utils.checkArgument(dDoubleValue < dDoubleValue2, "bucket boundaries not sorted.");
                        i++;
                        dDoubleValue = dDoubleValue2;
                    }
                }
            }

            public abstract List<Double> getBucketBoundaries();

            @Override // io.opencensus.metrics.export.Distribution.BucketOptions
            public final <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2) {
                return function.apply(this);
            }
        }
    }

    public static abstract class Bucket {
        Bucket() {
        }

        public static Bucket create(long j) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            return new AutoValue_Distribution_Bucket(j, null);
        }

        public static Bucket create(long j, Exemplar exemplar) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            Utils.checkNotNull(exemplar, "exemplar");
            return new AutoValue_Distribution_Bucket(j, exemplar);
        }

        public abstract long getCount();

        @Nullable
        public abstract Exemplar getExemplar();
    }
}
