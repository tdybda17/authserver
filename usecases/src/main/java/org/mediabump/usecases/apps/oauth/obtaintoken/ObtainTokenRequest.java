package org.mediabump.usecases.apps.oauth.obtaintoken;

import org.mediabump.usecases.request.Request;

public class ObtainTokenRequest implements Request {

    private String grantType;
    private String grantCode;
    private String redirectUri;
    private String clientId;
    private String clientSecret;

    public ObtainTokenRequest(
            String grantType,
            String grantCode,
            String redirectUri,
            String clientId,
            String clientSecret) {
        this.grantType = grantType;
        this.grantCode = grantCode;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getGrantCode() {
        return grantCode;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public void validate() {

    }
}
