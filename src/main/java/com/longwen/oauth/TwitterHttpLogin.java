package com.longwen.oauth;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class TwitterHttpLogin extends TwitterLogin{


    public static final String OAUTH_HEADER_AUTHTYPE = "OAuth ";
    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    public static final String OAUTH_SIGNATURE = "oauth_signature";
    public static final String OAUTH_NONCE = "oauth_nonce";
    public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
    public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    public static final String OAUTH_VERSION = "oauth_version";
    public static final String OAUTH_VERIFIER="oauth_verifier";
    public static final String HMAC_SHA1 = "HMAC-SHA1";
    public static final String ONE_DOT_OH = "1.0";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String AND = "&";

    private SecureRandom secureRandom = new SecureRandom();

    //以下两个参数是残缺的，以自身创建的项目为主
    private static final String consumer_key = "qFHm9xFUIqk2Hyf4";
    private static final String consumer_secret = "GGerXOGJwg7d6wWydbXskBuwbFYvZ9J";





    public static void main(String[] args) {

        TwitterLogin twitterLogin = new TwitterHttpLogin();

        /** 第一步获取request Token **/
       // RequestTokenResult requestTokenResult = (RequestTokenResult)twitterLogin.getRequestToken();

//        String oauth_token = "Gt9TVwAAAAABAa5RAAABbiHG144";
//        String oauth_token_secret = "l9t2FrQzasB8irqmIUgjZrPoJmbxnO2P";
//        String oauth_verifier = "DqtDbx0X0pUFtEpCqXXOrY5CbY7ZefIo";
//
//        /** 第二步 获取AccessToken **/
//        AccessTokenResult accessTokenResult = (AccessTokenResult) twitterLogin.getAccessToken(oauth_token,oauth_token_secret,oauth_verifier);


        /** 第三步 获取User信息   此步骤可以多次调用，通过 token多次获取**/
        String token = "275419-m5Z7Zyf4Hcpw4itXdzzw9kFkm2Nw"; //accessTokenResult.getOauthToken();
        String token_secret = "UrbUfHdlFqHZEy9xelGM6hTA2SNUFXR3mjmuw89S5TagJ"; //accessTokenResult.getOauthTokenSecret();


        JSONObject jsonObject = (JSONObject)twitterLogin.getVerifyCerdentials(token,token_secret);
        System.out.println("id:"+jsonObject.getString("id"));
        System.out.println("screenName:"+jsonObject.getString("screen_name"));



    }


    /**
     * 获取Twitter的 requestToken
     * @return
     */
    public Object getRequestToken(){
        long timestampSecs = this.generateTimestamp();
        String nonce =  this.generateNonce();
        //封装一堆参数
        Map<String, String> oauthHeaders = new HashMap();
        oauthHeaders.put(OAUTH_CONSUMER_KEY,consumer_key);
        oauthHeaders.put(OAUTH_SIGNATURE_METHOD, HMAC_SHA1);
        oauthHeaders.put(OAUTH_TIMESTAMP, Long.toString(timestampSecs));
        oauthHeaders.put(OAUTH_NONCE, nonce);
        oauthHeaders.put(OAUTH_VERSION, ONE_DOT_OH);
        //将参数和访问路径等元素，按照一定的规则进行拼装，然后进行加密和encode,此处比较复杂和容易出错。
        String header = getAuthorizationHeader(METHOD_POST,REQUEST_TOKEN_URL,"",oauthHeaders);
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Authorization",header);
        HttpResponse httpResponse= HttpRequest.post(REQUEST_TOKEN_URL).addHeaders(headers).execute();
        String data = httpResponse.body();
        System.out.println(data);

        RequestTokenResult requestTokenResult = new RequestTokenResult(data);
        StringBuilder url = new StringBuilder(AUTHORIZE);
        url.append("?oauth_token=").append(requestTokenResult.getOauthToken());
        url.append("&oauth_verifier=").append(requestTokenResult.getOauthTokenSecret());
        System.out.println("url:"+url.toString());
        //url.append("&callback_url=");
        //此处可根据返回值，来决定如何封装结果数据，异常的话会返回json格式。
        return requestTokenResult;
    }

    /***
     * 获取AccessToken的方法
     * @param oauth_token 通过requestToken获取的
     * @param oauth_token_secret 通过requestToken获取的
     * @param oauth_verifier 通过在Twitter上登录，返回URL中携带回参
     * @return
     */
    public Object getAccessToken(String oauth_token,String oauth_token_secret,String oauth_verifier){

        long timestampSecs = this.generateTimestamp();
        String nonce =  this.generateNonce();

        Map<String, String> oauthHeaders = new HashMap();
        oauthHeaders.put(OAUTH_CONSUMER_KEY, consumer_key);
        oauthHeaders.put(OAUTH_TOKEN, oauth_token);
        oauthHeaders.put(OAUTH_SIGNATURE_METHOD, HMAC_SHA1);
        oauthHeaders.put(OAUTH_TIMESTAMP, Long.toString(timestampSecs));
        oauthHeaders.put(OAUTH_NONCE, nonce);
        oauthHeaders.put(OAUTH_VERSION,ONE_DOT_OH);
        oauthHeaders.put(OAUTH_VERIFIER, oauth_verifier);

        String header = getAuthorizationHeader(METHOD_POST,ACCESS_TOKEN_URL,oauth_token_secret,oauthHeaders);
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Authorization",header);
        HttpResponse httpResponse= HttpRequest.post(ACCESS_TOKEN_URL).addHeaders(headers).execute();
        String data = httpResponse.body();
        System.out.println("accessToken result:"+data);
        if(data.startsWith("{")){
            JSONObject object = JSONObject.parseObject(httpResponse.body());
        }
        return new AccessTokenResult(data);
    }

    public Object getVerifyCerdentials(String token,String token_secret){
        long timestampSecs = this.generateTimestamp();
        String nonce =  this.generateNonce();

        Map<String, String> oauthHeaders = new HashMap();
        oauthHeaders.put(OAUTH_CONSUMER_KEY, consumer_key);
        oauthHeaders.put(OAUTH_TOKEN, token);
        oauthHeaders.put(OAUTH_SIGNATURE_METHOD, HMAC_SHA1);
        oauthHeaders.put(OAUTH_TIMESTAMP, Long.toString(timestampSecs));
        oauthHeaders.put(OAUTH_NONCE, nonce);
        oauthHeaders.put(OAUTH_VERSION,ONE_DOT_OH);
       // oauthHeaders.put(OAUTH_VERIFIER, token_secret);
        oauthHeaders.put("include_email", "false");
        String header = getAuthorizationHeader(METHOD_GET,VERIFY_CREDENTIALS_URL,token_secret,oauthHeaders);
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Authorization",header);
        HttpResponse httpResponse= HttpRequest.get(VERIFY_CREDENTIALS_URL).addHeaders(headers).execute();
        String data = httpResponse.body();
        System.out.println("VerifyCerdentials result:"+data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        return jsonObject;
    }





    private String getAuthorizationHeader(String method,String url,String oauth_secret,Map<String,String> param){
        StringBuffer header = new StringBuffer();
        header.append(method).append(AND).append(Signer.encode(url)).append(AND);

        StringBuffer paramstr = new StringBuffer();
        if(param.get(OAUTH_CONSUMER_KEY)!= null){
            paramstr.append(OAUTH_CONSUMER_KEY).append("=").append(param.get(OAUTH_CONSUMER_KEY)).append(AND);
        }
        if(param.get(OAUTH_NONCE)!= null){
            paramstr.append(OAUTH_NONCE).append("=").append(param.get(OAUTH_NONCE)).append(AND);
        }
        if(param.get(OAUTH_SIGNATURE_METHOD)!= null){
            paramstr.append(OAUTH_SIGNATURE_METHOD).append("=").append(param.get(OAUTH_SIGNATURE_METHOD)).append(AND);
        }
        if(param.get(OAUTH_TIMESTAMP)!= null){
            paramstr.append(OAUTH_TIMESTAMP).append("=").append(param.get(OAUTH_TIMESTAMP)).append(AND);
        }
        if(param.get(OAUTH_TOKEN)!= null){
            paramstr.append(OAUTH_TOKEN).append("=").append(param.get(OAUTH_TOKEN)).append(AND);
        }
        if(param.get(OAUTH_VERSION)!= null){
            paramstr.append(OAUTH_VERSION).append("=").append(param.get(OAUTH_VERSION));
        }

        header.append(Signer.encode(paramstr.toString()));

        Iterator iterator = param.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next().toString();
            param.put(key,quoted(param.get(key)));
        }
        String signerData=Signer.generateSignature(header.toString(),consumer_secret,oauth_secret);
        param.put(OAUTH_SIGNATURE, Signer.encode(signerData));
        return OAUTH_HEADER_AUTHTYPE
                + param.entrySet().stream().map(Map.Entry::toString).collect(Collectors.joining(", "));

    }



    private String quoted(String str) {
        return "\"" + str + "\"";
    }

    private long generateTimestamp() {
        long timestamp = System.currentTimeMillis();
        return timestamp / 1000L;
    }

    private String generateNonce() {
        return Long.toString(Math.abs(this.secureRandom.nextLong())) + System.currentTimeMillis();
    }



    class AccessTokenResult {
        private String oauthToken;
        private String oauthTokenSecret;
        private String userId;
        private String screenName;

        public AccessTokenResult(String data){
            String[] elments=data.split(AND);
            for(String elment : elments){
                String[] item =elment.split("=");
                if(item.length == 2){
                    if("oauth_token".equals(item[0])){
                        this.oauthToken = item[1];
                    }else if("oauth_token_secret".equals(item[0])){
                        this.oauthTokenSecret = item[1];
                    }else if("user_id".equals(item[0])){
                        this.userId = item[1];
                    }else if("screen_name".equals(item[0])){
                        this.screenName = item[1];
                    }
                }
            }
        }

        public String getOauthToken() {
            return oauthToken;
        }

        public String getOauthTokenSecret() {
            return oauthTokenSecret;
        }

        public String getUserId() {
            return userId;
        }

        public String getScreenName() {
            return screenName;
        }
    }

    class RequestTokenResult {
        private String oauthToken;
        private String oauthTokenSecret;
        public RequestTokenResult(String data){
            String[] elments=data.split(AND);
            for(String elment : elments){
                String[] item =elment.split("=");
                if(item.length == 2){
                    if("oauth_token".equals(item[0])){
                        this.oauthToken = item[1];
                    }else if("oauth_token_secret".equals(item[0])){
                        this.oauthTokenSecret = item[1];
                    }
                }
            }
        }

        public String getOauthToken() {
            return oauthToken;
        }

        public String getOauthTokenSecret() {
            return oauthTokenSecret;
        }
    }



}
