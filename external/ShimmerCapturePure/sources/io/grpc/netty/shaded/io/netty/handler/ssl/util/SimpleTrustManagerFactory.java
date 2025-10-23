package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.alts.CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes3.dex */
public abstract class SimpleTrustManagerFactory extends TrustManagerFactory {
    private static final FastThreadLocal<SimpleTrustManagerFactorySpi> CURRENT_SPI = new FastThreadLocal<SimpleTrustManagerFactorySpi>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public SimpleTrustManagerFactorySpi initialValue() {
            return new SimpleTrustManagerFactorySpi();
        }
    };
    private static final Provider PROVIDER;

    static {
        String str = "";
        PROVIDER = new Provider(str, 0.0d, str) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory.1
            private static final long serialVersionUID = -2680540247105807895L;
        };
    }

    protected SimpleTrustManagerFactory() {
        this("");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    protected SimpleTrustManagerFactory(String str) throws Throwable {
        FastThreadLocal<SimpleTrustManagerFactorySpi> fastThreadLocal = CURRENT_SPI;
        super(fastThreadLocal.get(), PROVIDER, str);
        fastThreadLocal.get().init(this);
        fastThreadLocal.remove();
        ObjectUtil.checkNotNull(str, "name");
    }

    protected abstract TrustManager[] engineGetTrustManagers();

    protected abstract void engineInit(KeyStore keyStore) throws Exception;

    protected abstract void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception;

    static final class SimpleTrustManagerFactorySpi extends TrustManagerFactorySpi {
        private SimpleTrustManagerFactory parent;
        private volatile TrustManager[] trustManagers;

        SimpleTrustManagerFactorySpi() {
        }

        private static void wrapIfNeeded(TrustManager[] trustManagerArr) {
            for (int i = 0; i < trustManagerArr.length; i++) {
                TrustManager trustManager = trustManagerArr[i];
                if ((trustManager instanceof X509TrustManager) && !CheckGcpEnvironment$$ExternalSyntheticApiModelOutline0.m$1(trustManager)) {
                    trustManagerArr[i] = new X509TrustManagerWrapper((X509TrustManager) trustManager);
                }
            }
        }

        void init(SimpleTrustManagerFactory simpleTrustManagerFactory) {
            this.parent = simpleTrustManagerFactory;
        }

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected void engineInit(KeyStore keyStore) throws KeyStoreException {
            try {
                this.parent.engineInit(keyStore);
            } catch (KeyStoreException e) {
                throw e;
            } catch (Exception e2) {
                throw new KeyStoreException(e2);
            }
        }

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
            try {
                this.parent.engineInit(managerFactoryParameters);
            } catch (InvalidAlgorithmParameterException e) {
                throw e;
            } catch (Exception e2) {
                throw new InvalidAlgorithmParameterException(e2);
            }
        }

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected TrustManager[] engineGetTrustManagers() {
            TrustManager[] trustManagerArrEngineGetTrustManagers = this.trustManagers;
            if (trustManagerArrEngineGetTrustManagers == null) {
                trustManagerArrEngineGetTrustManagers = this.parent.engineGetTrustManagers();
                if (PlatformDependent.javaVersion() >= 7) {
                    wrapIfNeeded(trustManagerArrEngineGetTrustManagers);
                }
                this.trustManagers = trustManagerArrEngineGetTrustManagers;
            }
            return (TrustManager[]) trustManagerArrEngineGetTrustManagers.clone();
        }
    }
}
