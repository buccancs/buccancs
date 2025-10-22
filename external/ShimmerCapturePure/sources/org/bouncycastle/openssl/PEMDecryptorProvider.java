package org.bouncycastle.openssl;

import org.bouncycastle.operator.OperatorCreationException;

/* loaded from: classes5.dex */
public interface PEMDecryptorProvider {
    PEMDecryptor get(String str) throws OperatorCreationException;
}
