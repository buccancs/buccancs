package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Preconditions;
import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

/* loaded from: classes.dex */
public class UserAuthorizer {
    static final URI DEFAULT_CALLBACK_URI = URI.create("/oauth2callback");
    private final String FETCH_TOKEN_ERROR;
    private final String TOKEN_STORE_ERROR;
    private final URI callbackUri;
    private final ClientId clientId;
    private final Collection<String> scopes;
    private final URI tokenServerUri;
    private final TokenStore tokenStore;
    private final HttpTransportFactory transportFactory;
    private final URI userAuthUri;

    private UserAuthorizer(ClientId clientId, Collection<String> collection, TokenStore tokenStore, URI uri, HttpTransportFactory httpTransportFactory, URI uri2, URI uri3) {
        this.TOKEN_STORE_ERROR = "Error parsing stored token data.";
        this.FETCH_TOKEN_ERROR = "Error reading result of Token API:";
        this.clientId = (ClientId) Preconditions.checkNotNull(clientId);
        this.scopes = ImmutableList.copyOf((Collection) Preconditions.checkNotNull(collection));
        this.callbackUri = uri == null ? DEFAULT_CALLBACK_URI : uri;
        this.transportFactory = httpTransportFactory == null ? OAuth2Utils.HTTP_TRANSPORT_FACTORY : httpTransportFactory;
        this.tokenServerUri = uri2 == null ? OAuth2Utils.TOKEN_SERVER_URI : uri2;
        this.userAuthUri = uri3 == null ? OAuth2Utils.USER_AUTH_URI : uri3;
        this.tokenStore = tokenStore == null ? new MemoryTokensStorage() : tokenStore;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public URI getCallbackUri() {
        return this.callbackUri;
    }

    public ClientId getClientId() {
        return this.clientId;
    }

    public Collection<String> getScopes() {
        return this.scopes;
    }

    public TokenStore getTokenStore() {
        return this.tokenStore;
    }

    public URI getCallbackUri(URI uri) {
        if (this.callbackUri.isAbsolute()) {
            return this.callbackUri;
        }
        if (uri == null || !uri.isAbsolute()) {
            throw new IllegalStateException("If the callback URI is relative, the baseUri passed must be an absolute URI");
        }
        return uri.resolve(this.callbackUri);
    }

    public URL getAuthorizationUrl(String str, String str2, URI uri) {
        URI callbackUri = getCallbackUri(uri);
        String strJoin = Joiner.on(' ').join(this.scopes);
        GenericUrl genericUrl = new GenericUrl(this.userAuthUri);
        genericUrl.put("response_type", (Object) "code");
        genericUrl.put("client_id", (Object) this.clientId.getClientId());
        genericUrl.put("redirect_uri", (Object) callbackUri);
        genericUrl.put("scope", (Object) strJoin);
        if (str2 != null) {
            genericUrl.put("state", (Object) str2);
        }
        genericUrl.put("access_type", (Object) "offline");
        genericUrl.put("approval_prompt", (Object) "force");
        if (str != null) {
            genericUrl.put("login_hint", (Object) str);
        }
        genericUrl.put("include_granted_scopes", (Object) true);
        return genericUrl.toURL();
    }

    public UserCredentials getCredentials(String str) throws IOException {
        Preconditions.checkNotNull(str);
        TokenStore tokenStore = this.tokenStore;
        if (tokenStore == null) {
            throw new IllegalStateException("Method cannot be called if token store is not specified.");
        }
        String strLoad = tokenStore.load(str);
        if (strLoad == null) {
            return null;
        }
        GenericJson json = OAuth2Utils.parseJson(strLoad);
        UserCredentials userCredentialsBuild = UserCredentials.newBuilder().setClientId(this.clientId.getClientId()).setClientSecret(this.clientId.getClientSecret()).setRefreshToken(OAuth2Utils.validateOptionalString(json, "refresh_token", "Error parsing stored token data.")).setAccessToken(new AccessToken(OAuth2Utils.validateString(json, "access_token", "Error parsing stored token data."), new Date(Long.valueOf(OAuth2Utils.validateLong(json, "expiration_time_millis", "Error parsing stored token data.")).longValue()))).setHttpTransportFactory(this.transportFactory).setTokenServerUri(this.tokenServerUri).build();
        monitorCredentials(str, userCredentialsBuild);
        return userCredentialsBuild;
    }

    public UserCredentials getCredentialsFromCode(String str, URI uri) throws IOException {
        Preconditions.checkNotNull(str);
        URI callbackUri = getCallbackUri(uri);
        GenericData genericData = new GenericData();
        genericData.put("code", (Object) str);
        genericData.put("client_id", (Object) this.clientId.getClientId());
        genericData.put("client_secret", (Object) this.clientId.getClientSecret());
        genericData.put("redirect_uri", (Object) callbackUri);
        genericData.put("grant_type", (Object) "authorization_code");
        HttpRequest httpRequestBuildPostRequest = this.transportFactory.create().createRequestFactory().buildPostRequest(new GenericUrl(this.tokenServerUri), new UrlEncodedContent(genericData));
        httpRequestBuildPostRequest.setParser(new JsonObjectParser(OAuth2Utils.JSON_FACTORY));
        GenericJson genericJson = (GenericJson) httpRequestBuildPostRequest.execute().parseAs(GenericJson.class);
        return UserCredentials.newBuilder().setClientId(this.clientId.getClientId()).setClientSecret(this.clientId.getClientSecret()).setRefreshToken(OAuth2Utils.validateOptionalString(genericJson, "refresh_token", "Error reading result of Token API:")).setAccessToken(new AccessToken(OAuth2Utils.validateString(genericJson, "access_token", "Error reading result of Token API:"), new Date(new Date().getTime() + (OAuth2Utils.validateInt32(genericJson, "expires_in", "Error reading result of Token API:") * 1000)))).setHttpTransportFactory(this.transportFactory).setTokenServerUri(this.tokenServerUri).build();
    }

    public UserCredentials getAndStoreCredentialsFromCode(String str, String str2, URI uri) throws IOException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        UserCredentials credentialsFromCode = getCredentialsFromCode(str2, uri);
        storeCredentials(str, credentialsFromCode);
        monitorCredentials(str, credentialsFromCode);
        return credentialsFromCode;
    }

    public void revokeAuthorization(String str) throws IOException {
        Preconditions.checkNotNull(str);
        TokenStore tokenStore = this.tokenStore;
        if (tokenStore == null) {
            throw new IllegalStateException("Method cannot be called if token store is not specified.");
        }
        String strLoad = tokenStore.load(str);
        if (strLoad == null) {
            return;
        }
        try {
            this.tokenStore.delete(str);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        GenericJson json = OAuth2Utils.parseJson(strLoad);
        String strValidateOptionalString = OAuth2Utils.validateOptionalString(json, "access_token", "Error parsing stored token data.");
        String strValidateOptionalString2 = OAuth2Utils.validateOptionalString(json, "refresh_token", "Error parsing stored token data.");
        if (strValidateOptionalString2 != null) {
            strValidateOptionalString = strValidateOptionalString2;
        }
        GenericUrl genericUrl = new GenericUrl(OAuth2Utils.TOKEN_REVOKE_URI);
        genericUrl.put("token", (Object) strValidateOptionalString);
        this.transportFactory.create().createRequestFactory().buildGetRequest(genericUrl).execute();
        if (e != null) {
            throw e;
        }
    }

    public void storeCredentials(String str, UserCredentials userCredentials) throws IOException {
        String tokenValue;
        Date expirationTime;
        if (this.tokenStore == null) {
            throw new IllegalStateException("Cannot store tokens if tokenStore is not specified.");
        }
        AccessToken accessToken = userCredentials.getAccessToken();
        if (accessToken != null) {
            tokenValue = accessToken.getTokenValue();
            expirationTime = accessToken.getExpirationTime();
        } else {
            tokenValue = null;
            expirationTime = null;
        }
        String refreshToken = userCredentials.getRefreshToken();
        GenericJson genericJson = new GenericJson();
        genericJson.setFactory(OAuth2Utils.JSON_FACTORY);
        genericJson.put("access_token", (Object) tokenValue);
        genericJson.put("expiration_time_millis", (Object) Long.valueOf(expirationTime.getTime()));
        if (refreshToken != null) {
            genericJson.put("refresh_token", (Object) refreshToken);
        }
        this.tokenStore.store(str, genericJson.toString());
    }

    protected void monitorCredentials(String str, UserCredentials userCredentials) {
        userCredentials.addChangeListener(new UserCredentialsListener(str));
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private URI callbackUri;
        private ClientId clientId;
        private Collection<String> scopes;
        private URI tokenServerUri;
        private TokenStore tokenStore;
        private HttpTransportFactory transportFactory;
        private URI userAuthUri;

        protected Builder() {
        }

        protected Builder(UserAuthorizer userAuthorizer) {
            this.clientId = userAuthorizer.clientId;
            this.scopes = userAuthorizer.scopes;
            this.transportFactory = userAuthorizer.transportFactory;
            this.tokenServerUri = userAuthorizer.tokenServerUri;
            this.tokenStore = userAuthorizer.tokenStore;
            this.callbackUri = userAuthorizer.callbackUri;
            this.userAuthUri = userAuthorizer.userAuthUri;
        }

        public URI getCallbackUri() {
            return this.callbackUri;
        }

        public Builder setCallbackUri(URI uri) {
            this.callbackUri = uri;
            return this;
        }

        public ClientId getClientId() {
            return this.clientId;
        }

        public Builder setClientId(ClientId clientId) {
            this.clientId = clientId;
            return this;
        }

        public HttpTransportFactory getHttpTransportFactory() {
            return this.transportFactory;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.transportFactory = httpTransportFactory;
            return this;
        }

        public Collection<String> getScopes() {
            return this.scopes;
        }

        public Builder setScopes(Collection<String> collection) {
            this.scopes = collection;
            return this;
        }

        public URI getTokenServerUri() {
            return this.tokenServerUri;
        }

        public Builder setTokenServerUri(URI uri) {
            this.tokenServerUri = uri;
            return this;
        }

        public TokenStore getTokenStore() {
            return this.tokenStore;
        }

        public Builder setTokenStore(TokenStore tokenStore) {
            this.tokenStore = tokenStore;
            return this;
        }

        public URI getUserAuthUri() {
            return this.userAuthUri;
        }

        public Builder setUserAuthUri(URI uri) {
            this.userAuthUri = uri;
            return this;
        }

        public UserAuthorizer build() {
            return new UserAuthorizer(this.clientId, this.scopes, this.tokenStore, this.callbackUri, this.transportFactory, this.tokenServerUri, this.userAuthUri);
        }
    }

    private class UserCredentialsListener implements OAuth2Credentials.CredentialsChangedListener {
        private final String userId;

        public UserCredentialsListener(String str) {
            this.userId = str;
        }

        @Override // com.google.auth.oauth2.OAuth2Credentials.CredentialsChangedListener
        public void onChanged(OAuth2Credentials oAuth2Credentials) throws IOException {
            UserAuthorizer.this.storeCredentials(this.userId, (UserCredentials) oAuth2Credentials);
        }
    }
}
