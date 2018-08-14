package com.eqy.utils;

import org.apache.log4j.Logger;
import org.json.JSONObject;


/**
 * 
 *根据回调code 发送请求得到用户openid
 * @author  刘伟
 * @version  [版本号, 2015-1-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HttpGetOpenId
{
    
    //移动 APP_ID SECRET 
    public static final String APP_ID = "";
    public static final String SECRET = "";
    public static final String  URL ="https://api.weixin.qq.com/sns/oauth2/access_token";
    // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    
    //用户是否关注
    public static final String  IS_ATTENT ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
    //网页授权access_token
    public static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+SECRET+"&code=#{CODE}&grant_type=authorization_code";
    protected static final Logger logger = Logger.getLogger( HttpGetOpenId.class);
    
    
    public static String getOpenIdSendUrl(String code){
        System.out.println("--------------------:");
        StringBuffer httParam = new StringBuffer();
        httParam.append("?");
        httParam.append("appid="+APP_ID +"&");
        httParam.append("secret=" + SECRET +"&");
        httParam.append("code="+code + "&");
        httParam.append("grant_type=authorization_code");
        //发送请求
        String result = HttpSendUtils.sendGet(URL+httParam.toString());
        System.out.println("--------------------:"+URL+httParam.toString());
        System.out.println("--------------------:"+result);
        
        logger.info("geturl =======" + URL+httParam.toString());
        JSONObject jsonResult = JSONUtils.toJSONObject(result);
        logger.info("getOpenIdSendUrl返回：result"+jsonResult.toString());
        String openid = jsonResult.optString("openid");

        
        return openid ;
    }
    
    /*
     * 看用户是否关注
     */
 public static String getSubscribeIsAttend(String access_token,String openid){
        //?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        StringBuffer httParam = new StringBuffer();
        httParam.append(access_token);
        httParam.append("&");
        httParam.append("openid=");
        httParam.append(openid+"&lang=zh_CN");
        //发送请求
        String result = HttpSendUtils.sendGet(IS_ATTENT+httParam.toString());
        JSONObject jsonResult = JSONUtils.toJSONObject(result);
        logger.info("getOpenIdSendUrl返回结果：result"+jsonResult.toString());
        String subscribe = jsonResult.optString("subscribe");
        logger.info("调用关注接口返回subscribe ====="+subscribe);
        return subscribe ;
    }
 
     /*
      * 获取网页授权access_token,跟用户信息
      */
     public static String get_UserInfo(String code){
         String url = GET_ACCESS_TOKEN.replace("#{CODE}",code);
         String result = HttpSendUtils.sendGet(url);
         JSONObject jsonResult = JSONUtils.toJSONObject(result);
         logger.info("GET_ACCESS_TOKEN.：result"+jsonResult.toString());
         String openid = jsonResult.optString("openid");
         String access_token  = jsonResult.optString("access_token");
         if(openid != null || access_token != null){
             //获取用户的详情
             url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
             
             result = HttpSendUtils.sendGet(url);
             logger.info("获取用户的详情：result"+result);
         }else{
             result = null;
         }
         return result;
     }
}
