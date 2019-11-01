package com.longwen.oauth;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Twitter4jLogin extends TwitterLogin{

    Twitter twitter = new TwitterFactory().getInstance();




    public static void main(String[] args) {
        TwitterLogin twitterLogin = new Twitter4jLogin();

          /** 第一步获取request Token**/
    //    RequestToken requestToken = (RequestToken) twitterLogin.getRequestToken();


          /** 第二步 获取AccessToken **/
//          String oauth_token = "aK4K4wAAAAABAa5RAAABbiG5n9I";
//          String oauth_tokenSecret = "LQxBtKYegmMK7CIOZVnprthRTDqn7e7r";
//          String oauthVerifier = "xZrWLjgqY990T84yjEKUuSOWuSvXjlup";
//
//          AccessToken accessToken = (AccessToken) twitterLogin.getAccessToken(oauth_token,oauth_tokenSecret,oauthVerifier);
//          System.out.println("token:"+accessToken.getToken());
//          System.out.println("tokenSecret:"+accessToken.getTokenSecret());

          /** 第三步 获取User信息   此步骤可以多次调用，通过 token多次获取**/
          String token = "2754111111-m5Z7Zyf4Hcpw4itXdzzw9kFkm2Nwk";
          String tokenSecret = "UrbUfHdlFqHZEy9xelGM6hTA2SNUFXR3";
          User user =(User)twitterLogin.getVerifyCerdentials(token,tokenSecret);
          System.out.println("id:" + user.getId());
          System.out.println("screenName:" + user.getScreenName());

    }

    public Object getRequestToken(){
        RequestToken requestToken = null;
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        StringBuilder url = new StringBuilder(AUTHORIZE);
        url.append("?oauth_token=").append(requestToken.getToken());
        url.append("&oauth_verifier=").append(requestToken.getTokenSecret());

        System.out.println("oauth_token:"+requestToken.getToken());
        System.out.println("oauth_tokenSecret:"+requestToken.getTokenSecret());

        //去浏览器访问以下链接，可以获取返回的 oauthVerifier值
        System.out.println("authorize 访问地址："+requestToken.getAuthorizationURL());
        return requestToken;

    }

    public Object getAccessToken(String oauth_token,String oauth_token_secret,String oauth_verifier)
    {
        AccessToken accessToken = null;
        try {
            RequestToken requestToken = new RequestToken(oauth_token,oauth_token_secret);
            accessToken = twitter.getOAuthAccessToken(requestToken,oauth_verifier);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return accessToken;



    }

    public Object getVerifyCerdentials(String token,String token_secret){
        AccessToken accessToken = new AccessToken(token,token_secret);
        twitter.setOAuthAccessToken(accessToken);
        User user = null;
        try {
            user = twitter.verifyCredentials();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return user;
    }



}
