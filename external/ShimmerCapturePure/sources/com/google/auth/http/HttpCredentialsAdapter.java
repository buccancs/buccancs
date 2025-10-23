package com.google.auth.http;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.Preconditions;
import com.google.auth.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class HttpCredentialsAdapter implements HttpRequestInitializer, HttpUnsuccessfulResponseHandler {
    private static final Logger LOGGER = Logger.getLogger(HttpCredentialsAdapter.class.getName());
    private static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");
    private final Credentials credentials;

    public HttpCredentialsAdapter(Credentials credentials) {
        Preconditions.checkNotNull(credentials);
        this.credentials = credentials;
    }

    @Override // com.google.api.client.http.HttpRequestInitializer
    public void initialize(HttpRequest httpRequest) throws IOException {
        httpRequest.setUnsuccessfulResponseHandler(this);
        if (this.credentials.hasRequestMetadata()) {
            HttpHeaders headers = httpRequest.getHeaders();
            Map<String, List<String>> requestMetadata = this.credentials.getRequestMetadata(httpRequest.getUrl() != null ? httpRequest.getUrl().toURI() : null);
            if (requestMetadata == null) {
                return;
            }
            for (Map.Entry<String, List<String>> entry : requestMetadata.entrySet()) {
                String key = entry.getKey();
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(entry.getValue());
                headers.put(key, (Object) arrayList);
            }
        }
    }

    @Override // com.google.api.client.http.HttpUnsuccessfulResponseHandler
    public boolean handleResponse(HttpRequest httpRequest, HttpResponse httpResponse, boolean z) {
        boolean zFind;
        boolean z2;
        List<String> authenticateAsList = httpResponse.getHeaders().getAuthenticateAsList();
        if (authenticateAsList != null) {
            for (String str : authenticateAsList) {
                if (str.startsWith("Bearer ")) {
                    zFind = INVALID_TOKEN_ERROR.matcher(str).find();
                    z2 = true;
                    break;
                }
            }
            zFind = false;
            z2 = false;
        } else {
            zFind = false;
            z2 = false;
        }
        if (z2 ? zFind : httpResponse.getStatusCode() == 401) {
            try {
                this.credentials.refresh();
                initialize(httpRequest);
                return true;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "unable to refresh token", (Throwable) e);
            }
        }
        return false;
    }
}
