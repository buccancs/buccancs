package org.conscrypt;

/* loaded from: classes5.dex */
abstract class PeerInfoProvider {
    private static final PeerInfoProvider NULL_PEER_INFO_PROVIDER = new PeerInfoProvider() { // from class: org.conscrypt.PeerInfoProvider.1
        @Override
            // org.conscrypt.PeerInfoProvider
        String getHostname() {
            return null;
        }

        @Override // org.conscrypt.PeerInfoProvider
        public String getHostnameOrIP() {
            return null;
        }

        @Override // org.conscrypt.PeerInfoProvider
        public int getPort() {
            return -1;
        }
    };

    PeerInfoProvider() {
    }

    static PeerInfoProvider nullProvider() {
        return NULL_PEER_INFO_PROVIDER;
    }

    static PeerInfoProvider forHostAndPort(final String str, final int i) {
        return new PeerInfoProvider() { // from class: org.conscrypt.PeerInfoProvider.2
            @Override
                // org.conscrypt.PeerInfoProvider
            String getHostname() {
                return str;
            }

            @Override // org.conscrypt.PeerInfoProvider
            public String getHostnameOrIP() {
                return str;
            }

            @Override // org.conscrypt.PeerInfoProvider
            public int getPort() {
                return i;
            }
        };
    }

    abstract String getHostname();

    abstract String getHostnameOrIP();

    abstract int getPort();
}
