package com.longwen.oauth;


public abstract class TwitterLogin {


    protected static final String REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token";
    protected static final String AUTHORIZE = "https://api.twitter.com/oauth/authenticate";
    protected static final String ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
    protected static final String VERIFY_CREDENTIALS_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";


    abstract public Object getRequestToken();

    abstract public Object getAccessToken(String oauth_token,String oauth_token_secret,String oauth_verifier);

    abstract public Object getVerifyCerdentials(String token,String token_secret);



}
