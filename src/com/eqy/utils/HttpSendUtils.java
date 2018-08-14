package com.eqy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.eqy.constants.SystemConstants;

public class HttpSendUtils {

	protected static final Logger logger = Logger
			.getLogger(HttpSendUtils.class);

	/**
	 * GET请求，url包含参数数据
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		// 构造httpclient的实例
		HttpClient htpc = new HttpClient();
		// 创建Get方法的实例

		// url需要传递参数并包含中文时，可以将参数转码（URLEncoder.encode(参数,"UTF-8")），与服务器端一样的编码格式
		String responseinfo = "";
		GetMethod getMethod = null ;
		
		try {
			getMethod = new GetMethod(url); // 链接的路径
			getMethod.getParams().setContentCharset("UTF-8");  
			// 使用系统提供的默认的恢复策略,此处HttpClient的恢复策略可以自定义（通过实现接口HttpMethodRetryHandler来实现）。
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			getMethod.setRequestHeader("Connection", "close");
			// 执行getMethod
			int statusCode = htpc.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("method failed : " + getMethod.getStatusLine());
				logger.info("--------------------->>method failed : " + getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			//System.out.println(new String(responseBody));
			responseinfo = new String(responseBody,"UTF-8");
		} catch (Exception e) {
			logger.info("--------------------->>出现了网络异常");
			e.printStackTrace();
		}finally {
			// 释放连接
			getMethod.releaseConnection();
		
		}

		return responseinfo;
	}

	public static String sendPost(String url, String param) {
		
		String result = "";
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl
					.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("content-type",
			     "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("charset", "UTF-8");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
//			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			OutputStreamWriter out = new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8");
//			out.print(param);
			out.write(param);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
				
			}
			in.close();
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
	public static void main(String[] args) {

		
	}
}
