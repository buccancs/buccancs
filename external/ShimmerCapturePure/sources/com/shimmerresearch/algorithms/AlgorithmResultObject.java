package com.shimmerresearch.algorithms;

import com.shimmerresearch.algorithms.AbstractAlgorithm;

/* loaded from: classes2.dex */
public class AlgorithmResultObject {
    public AbstractAlgorithm.ALGORITHM_RESULT_TYPE mAlgorithmResultType;
    public Object mResult;
    public String mTrialName;

    public AlgorithmResultObject(AbstractAlgorithm.ALGORITHM_RESULT_TYPE algorithm_result_type, Object obj) {
        this.mAlgorithmResultType = algorithm_result_type;
        this.mResult = obj;
    }

    public AlgorithmResultObject(AbstractAlgorithm.ALGORITHM_RESULT_TYPE algorithm_result_type, Object obj, String str) {
        this(algorithm_result_type, obj);
        this.mTrialName = str;
    }
}
