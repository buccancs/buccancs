package com.google.auth.oauth2;

import androidx.core.app.NotificationCompat;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.IdTokenProvider;
import com.google.common.base.MoreObjects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class ComputeEngineCredentials extends GoogleCredentials implements ServiceAccountSigner, IdTokenProvider {
    static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
    static final String DEFAULT_METADATA_SERVER_URL = "http://metadata.google.internal";
    static final int MAX_COMPUTE_PING_TRIES = 3;
    static final String SIGN_BLOB_URL_FORMAT = "https://iamcredentials.googleapis.com/v1/projects/-/serviceAccounts/%s:signBlob";
    private static final String GOOGLE = "Google";
    private static final Logger LOGGER = Logger.getLogger(ComputeEngineCredentials.class.getName());
    private static final String METADATA_FLAVOR = "Metadata-Flavor";
    private static final String PARSE_ERROR_ACCOUNT = "Error parsing service account response. ";
    private static final String PARSE_ERROR_PREFIX = "Error parsing token refresh response. ";
    private static final long serialVersionUID = -4113476462526554235L;
    private final String transportFactoryClassName;
    private transient String serviceAccountEmail;
    private transient HttpTransportFactory transportFactory;

    private ComputeEngineCredentials(HttpTransportFactory httpTransportFactory) {
        HttpTransportFactory httpTransportFactory2 = (HttpTransportFactory) MoreObjects.firstNonNull(httpTransportFactory, getFromServiceLoader(HttpTransportFactory.class, OAuth2Utils.HTTP_TRANSPORT_FACTORY));
        this.transportFactory = httpTransportFactory2;
        this.transportFactoryClassName = httpTransportFactory2.getClass().getName();
    }

    public static ComputeEngineCredentials create() {
        return new ComputeEngineCredentials(null);
    }

    static boolean runningOnComputeEngine(HttpTransportFactory httpTransportFactory, DefaultCredentialsProvider defaultCredentialsProvider) {
        if (Boolean.parseBoolean(defaultCredentialsProvider.getEnv("NO_GCE_CHECK"))) {
            return false;
        }
        GenericUrl genericUrl = new GenericUrl(getMetadataServerUrl(defaultCredentialsProvider));
        for (int i = 1; i <= 3; i++) {
            try {
                HttpRequest httpRequestBuildGetRequest = httpTransportFactory.create().createRequestFactory().buildGetRequest(genericUrl);
                httpRequestBuildGetRequest.setConnectTimeout(500);
                httpRequestBuildGetRequest.getHeaders().set(METADATA_FLAVOR, GOOGLE);
                HttpResponse httpResponseExecute = httpRequestBuildGetRequest.execute();
                try {
                    return OAuth2Utils.headersContainValue(httpResponseExecute.getHeaders(), METADATA_FLAVOR, GOOGLE);
                } finally {
                    httpResponseExecute.disconnect();
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException e) {
                LOGGER.log(Level.FINE, "Encountered an unexpected exception when determining if we are running on Google Compute Engine.", (Throwable) e);
            }
        }
        LOGGER.log(Level.INFO, "Failed to detect whether we are running on Google Compute Engine.");
        return false;
    }

    public static String getMetadataServerUrl(DefaultCredentialsProvider defaultCredentialsProvider) {
        String env = defaultCredentialsProvider.getEnv("GCE_METADATA_HOST");
        if (env == null) {
            return DEFAULT_METADATA_SERVER_URL;
        }
        return "http://" + env;
    }

    public static String getMetadataServerUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getTokenServerEncodedUrl(DefaultCredentialsProvider defaultCredentialsProvider) {
        return getMetadataServerUrl(defaultCredentialsProvider) + "/computeMetadata/v1/instance/service-accounts/default/token";
    }

    public static String getTokenServerEncodedUrl() {
        return getTokenServerEncodedUrl(DefaultCredentialsProvider.DEFAULT);
    }

    public static String getServiceAccountsUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT) + "/computeMetadata/v1/instance/service-accounts/?recursive=true";
    }

    public static String getIdentityDocumentUrl() {
        return getMetadataServerUrl(DefaultCredentialsProvider.DEFAULT) + "/computeMetadata/v1/instance/service-accounts/default/identity";
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public AccessToken refreshAccessToken() throws IOException {
        HttpResponse metadataResponse = getMetadataResponse(getTokenServerEncodedUrl());
        int statusCode = metadataResponse.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified. It is possible to skip checking for Compute Engine metadata by specifying the environment  variable NO_GCE_CHECK=true.", Integer.valueOf(statusCode)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", Integer.valueOf(statusCode), metadataResponse.parseAsString()));
        }
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return new AccessToken(OAuth2Utils.validateString((GenericData) metadataResponse.parseAs(GenericData.class), "access_token", PARSE_ERROR_PREFIX), new Date(this.clock.currentTimeMillis() + (OAuth2Utils.validateInt32(r0, "expires_in", PARSE_ERROR_PREFIX) * 1000)));
    }

    @Override // com.google.auth.oauth2.IdTokenProvider
    public IdToken idTokenWithAudience(String str, List<IdTokenProvider.Option> list) throws IOException {
        GenericUrl genericUrl = new GenericUrl(getIdentityDocumentUrl());
        if (list != null) {
            if (list.contains(IdTokenProvider.Option.FORMAT_FULL)) {
                genericUrl.set("format", (Object) "full");
            }
            if (list.contains(IdTokenProvider.Option.LICENSES_TRUE)) {
                genericUrl.set("format", (Object) "full");
                genericUrl.set("license", (Object) "TRUE");
            }
        }
        genericUrl.set("audience", (Object) str);
        HttpResponse metadataResponse = getMetadataResponse(genericUrl.toString());
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return IdToken.create(metadataResponse.parseAsString());
    }

    private HttpResponse getMetadataResponse(String str) throws IOException {
        HttpRequest httpRequestBuildGetRequest = this.transportFactory.create().createRequestFactory().buildGetRequest(new GenericUrl(str));
        httpRequestBuildGetRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        httpRequestBuildGetRequest.getHeaders().set(METADATA_FLAVOR, (Object) GOOGLE);
        httpRequestBuildGetRequest.setThrowExceptionOnExecuteError(false);
        try {
            return httpRequestBuildGetRequest.execute();
        } catch (UnknownHostException e) {
            throw new IOException("ComputeEngineCredentials cannot find the metadata server. This is likely because code is not running on Google Compute Engine.", e);
        }
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(this.transportFactoryClassName);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public String toString() {
        return MoreObjects.toStringHelper(this).add("transportFactoryClassName", this.transportFactoryClassName).toString();
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (obj instanceof ComputeEngineCredentials) {
            return Objects.equals(this.transportFactoryClassName, ((ComputeEngineCredentials) obj).transportFactoryClassName);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.transportFactory = (HttpTransportFactory) newInstance(this.transportFactoryClassName);
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override // com.google.auth.ServiceAccountSigner
    public String getAccount() {
        if (this.serviceAccountEmail == null) {
            try {
                this.serviceAccountEmail = getDefaultServiceAccount();
            } catch (IOException e) {
                throw new RuntimeException("Failed to get service account", e);
            }
        }
        return this.serviceAccountEmail;
    }

    @Override // com.google.auth.ServiceAccountSigner
    public byte[] sign(byte[] bArr) {
        try {
            return IamUtils.sign(getAccount(), this, this.transportFactory.create(), bArr, Collections.emptyMap());
        } catch (ServiceAccountSigner.SigningException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw new ServiceAccountSigner.SigningException("Signing failed", e2);
        }
    }

    private String getDefaultServiceAccount() throws IOException {
        HttpResponse metadataResponse = getMetadataResponse(getServiceAccountsUrl());
        int statusCode = metadataResponse.getStatusCode();
        if (statusCode == 404) {
            throw new IOException(String.format("Error code %s trying to get service accounts from Compute Engine metadata. This may be because the virtual machine instance does not have permission scopes specified.", Integer.valueOf(statusCode)));
        }
        if (statusCode != 200) {
            throw new IOException(String.format("Unexpected Error code %s trying to get service accounts from Compute Engine metadata: %s", Integer.valueOf(statusCode), metadataResponse.parseAsString()));
        }
        if (metadataResponse.getContent() == null) {
            throw new IOException("Empty content from metadata token server request.");
        }
        return OAuth2Utils.validateString(OAuth2Utils.validateMap((GenericData) metadataResponse.parseAs(GenericData.class), "default", PARSE_ERROR_ACCOUNT), NotificationCompat.CATEGORY_EMAIL, PARSE_ERROR_ACCOUNT);
    }

    public static class Builder extends GoogleCredentials.Builder {
        private HttpTransportFactory transportFactory;

        protected Builder() {
        }

        protected Builder(ComputeEngineCredentials computeEngineCredentials) {
            this.transportFactory = computeEngineCredentials.transportFactory;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        @Override // com.google.auth.oauth2.GoogleCredentials.Builder, com.google.auth.oauth2.OAuth2Credentials.Builder
        public ComputeEngineCredentials build() {
            return new ComputeEngineCredentials(this.transportFactory);
        }
    }
}
