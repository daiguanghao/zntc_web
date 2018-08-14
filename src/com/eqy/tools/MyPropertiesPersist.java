package com.eqy.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.springframework.util.DefaultPropertiesPersister;

/**
 * 功能简述：数据库加密解密
 * @author luoyb
 * @version 2017-4-1
 * @see MyPropertiesPersist
 * @since
 */
public class MyPropertiesPersist extends DefaultPropertiesPersister
{
    public void load(Properties props, InputStream is) throws IOException
    {
        Properties properties = new Properties();
        properties.load(is);
        /*if ((properties.get("jdbc.password") != null))
        {
            // 这里通过解密算法，得到你的真实密码，然后写入到properties中
            DESTools des = DESTools.getInstace();
            des = DESTools.createInstace("kqzx12345678");
            String username = des.getDesString(properties.getProperty("jdbc.username"));
            String password = des.getDesString(properties.getProperty("jdbc.password"));
            properties.setProperty("jdbc.username", username);
            properties.setProperty("jdbc.password", password);
        }*/
        OutputStream outputStream = null;
        try
        {
            outputStream = new ByteArrayOutputStream();
            properties.store(outputStream, "");
            is = outStream2InputStream(outputStream);
            super.load(props, is);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (outputStream != null)
            {
                outputStream.close();
            }
        }
    }
    private InputStream outStream2InputStream(OutputStream out)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos = (ByteArrayOutputStream)out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(bos.toByteArray());
        return swapStream;
    }
}