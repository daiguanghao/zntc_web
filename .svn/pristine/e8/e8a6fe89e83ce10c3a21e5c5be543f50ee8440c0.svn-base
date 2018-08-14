package com.eqy.tools;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;

/**
 * @ClassName: RequestUtil
 * @Description: Request请求工具类
 * @author zz
 * @date 2016-9-19 上午9:24:15
 */
public class RequestUtil extends ServletRequestUtils
{
    static Map<String, String> ENCRYPT_PARAM_MAP = new HashMap<String, String>();

    /**
     * 对选择多个项目 进行批处理操作
     * 
     * @param request
     * @param name
     * @return 返回以","分割的TeamId
     */
    public static String getStringsParameter(HttpServletRequest request, String name, String defaultVal)
    {
        String strResult = defaultVal;
        try
        {
            StringBuffer bufResult = new StringBuffer();
            String[] arrayTemp = getStringParameters(request, name);

            if (arrayTemp != null && arrayTemp.length > 0)
            {
                for (int num = 0; num < arrayTemp.length; num++)
                {
                    if (num == arrayTemp.length - 1)
                    {
                        bufResult.append(arrayTemp[num]);
                    }
                    else
                    {
                        bufResult.append(arrayTemp[num] + ",");
                    }
                }
            }
            strResult = bufResult.toString();
        }
        catch (Exception e)
        {
            strResult = defaultVal;
        }
        return strResult;
    }

    /**
     * 解决中文乱码问题
     * 
     * @param request
     * @param name
     * @param defaultVal
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getStrParam(ServletRequest request, String name, String defaultVal) throws UnsupportedEncodingException
    {
        String str = getStringParameter(request, name, defaultVal);
        str = new String(str.getBytes("8859_1"), "UTF-8");
        return str;
    }

}
