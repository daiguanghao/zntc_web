package com.eqy.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.params.CoreConnectionPNames;

/**
* @ClassName: LockHttpClient
* @Description: http调用
* @author luoyb
* @date 2017-8-7 下午3:40:35
*
*/
@SuppressWarnings("deprecation")
public class LockHttpClient
{
    public static String doPostRequest(String url, String jsonData)
    {
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        int statusCode = 0;
        String response = "";
        try
        {
            httpClient = new HttpClient();
            // 连接超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
            // 传递参数
            StringRequestEntity entity = new StringRequestEntity(jsonData, "text/json", "utf-8");
            postMethod.setRequestEntity(entity);
            statusCode = httpClient.executeMethod(postMethod);
            System.out.println("statusCode==============>" + statusCode);
            if (statusCode == HttpStatus.SC_OK)
            {
                // statusCode=200 返回成功
                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                String tmp = null;
                // 读取返回报文
                StringBuffer resp = new StringBuffer();
                try
                {
                    inputStream = postMethod.getResponseBodyAsStream();
                    inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    bufferedReader = new BufferedReader(inputStreamReader);
                    while ((tmp = bufferedReader.readLine()) != null)
                    {
                        resp.append(tmp).append("\n");
                    }
                    response = resp.toString();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    // 关闭client链接 关闭流
                    postMethod.releaseConnection();
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly(inputStreamReader);
                    IOUtils.closeQuietly(bufferedReader);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }
    public static String doGetRequest(String url, String jsonData)
    {
        HttpClient httpClient = null;
        GetMethod getMethod = null;
        int statusCode = 0;
        String response = "";
        try
        {
            httpClient = new HttpClient();
            // 连接超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
            getMethod = new GetMethod(url);
            // 请求超时
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
            statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK)
            {
                // statusCode=200 返回成功
                try
                {
                    Header[] headers = getMethod.getResponseHeaders();
                    for (Header h : headers)
                        System.out.println(h.getName() + "------------ " + h.getValue());
                    // 读取 HTTP 响应内容，这里简单打印网页内容
                    byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
                    response = new String(responseBody, "UTF-8");
                    System.out.println("----------response:" + response);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    // 关闭client链接 关闭流
                    getMethod.releaseConnection();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }
}
