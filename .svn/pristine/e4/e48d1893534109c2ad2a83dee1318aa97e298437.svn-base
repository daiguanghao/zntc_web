package com.eqy.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUtil
 * @Description: 文件处理工具类
 * @author zz
 * @date 2016-9-14 上午11:54:45
 */
public class FileUtil
{
    /**
     * @Title: uploadFile
     * @Description: 文件上传
     * @param file
     * @param filePath
     *            文件全路径,包含文件名
     * @throws IOException
     */
    public static void uploadFile(MultipartFile file, String filePath) throws IOException
    {
    	File newFile = new File(filePath);
    	
    	    try {
    	    	//判断父目录路径是否存在，如test.txt前的I:\a\b\
    	    	if (!newFile.getParentFile().exists()) {
	    	    	//不存在则创建父目录
	    	    	newFile.getParentFile().mkdirs();
    	    	}
    	    	FileOutputStream out = new FileOutputStream(new File(filePath));
    	    	out.write(file.getBytes());
    	    	out.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }
    /**
     * @Title: uploadFile_rename
     * @Description: 上传文件(重命名)
     * @param file
     * @param filePath
     * @return
     */
    public static String uploadFile_rename(MultipartFile file, StringBuffer filePath)
    {
        String newFileName = null;
        try
        {
            File dir = new File(filePath.toString());// 文件保存路径
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            int dot = originalFilename.lastIndexOf(".");
            String extension = "";
            if (dot > 0)
            {
                extension = originalFilename.substring(dot, originalFilename.length());
            }
            newFileName = UUID.randomUUID().toString() + extension;// 新的文件名
            filePath.append("/" + newFileName);
            File out = new File(filePath.toString());
            FileCopyUtils.copy(file.getBytes(), out);// 上传
            filePath.delete(0, filePath.length());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return newFileName;
    }
    /**
     * @Title: downloadFile
     * @Description: 下载文件
     * @param response
     * @param urlPath
     *            文件完整路径(包括文件名和扩展名)
     * @param filePath
     *            下载后看到的文件名
     * @throws Exception
     */
    public static void downloadFile(String urlPath, String filePath) throws Exception
    {
        File dirFile = new File(filePath);
        if (!dirFile.exists())
        {
            // 文件路径不存在时，自动创建目录
            dirFile.mkdir();
        }
        // 构造URL
        URL url = new URL(urlPath);
        // 打开连接
        URLConnection connection = url.openConnection();
        // 输入流
        InputStream in = connection.getInputStream();
        // 输出的文件流
        FileOutputStream os = new FileOutputStream(filePath);
        // 1K的数据缓冲
        byte[] buffer = new byte[1024];
        // 读取到的数据长度
        int read;
        // 开始读取
        while ((read = in.read(buffer)) > 0)
        {
            os.write(buffer, 0, read);
        }
        // 完毕，关闭所有链接
        os.close();
        in.close();
    }
    /**
     * @Title: fileDownload
     * @Description: 下载文件
     * @param request
     * @param response
     * @param storeName
     * @param contentType
     * @param realName
     * @throws Exception
     */
    public static void
        fileDownload(HttpServletRequest request, HttpServletResponse response, String storeName,
                     String contentType, String realName) throws Exception
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String ctxPath = request.getSession().getServletContext().getRealPath("");
        String downLoadPath = ctxPath + storeName;
        long fileLength = new File(downLoadPath).length();
        response.setContentType(contentType);
        response.setHeader("Content-disposition",
            "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
        {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
    public static String getFileSizeToKB(Long size)
    {
        String result = "0";
        if (size != null)
        {
            DecimalFormat df2 = new DecimalFormat("###.0");
            result = df2.format(size / 1024.0);
        }
        return result;
    }
    // 导入群发时读取
    public static List<String> readFile(MultipartFile file)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            // File temp= new File("temp.txt");
            // file.transferTo(temp);
            InputStream in = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = null;
            while ((s = br.readLine()) != null)
            {// 使用readLine方法，一次读一行
                list.add(s);
            }
            br.close();
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @Title: removeFile
     * @Description: 删除文件
     * @param request
     * @param fileUrl
     */
    public static void removeFile(HttpServletRequest request, String fileUrl)
    {
        try
        {
            boolean b = false;
            String realPath = request.getSession().getServletContext().getRealPath("");
            String filePath = realPath + fileUrl;
            File file = new File(filePath);
            if (file != null && file.exists() && file.isFile())
            {
                b = file.delete();
            }
            System.out.println(" remove file result  = " + b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static boolean isNumber(String tel)
    {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match = pattern.matcher(tel.replaceAll(" ", ""));
        if (match.matches() == false)
        {
            return false;
        }
        return true;
    }
    /**
     * 读写文件 - sql load control
     * 
     * @param request
     * @param fileName
     * @param tableName
     */
    public static boolean modifyFile(HttpServletRequest request, String fileName, String tableName)
    {
        try
        {
            BufferedReader file1 = new BufferedReader(new FileReader(
                request.getSession().getServletContext().getRealPath("/resources/control_m.ctl")));
            BufferedWriter file2 = new BufferedWriter(new FileWriter(
                request.getSession().getServletContext().getRealPath("/resources/control.ctl")));
            // BufferedReader file1 = new BufferedReader(new
            // FileReader("F:\\Soft\\apache-tomcat-6.0.37\\webapps\\xwwt_web\\resources\\control_m.ctl"));
            // BufferedWriter file2 = new BufferedWriter(new
            // FileWriter("F:\\Soft\\apache-tomcat-6.0.37\\webapps\\xwwt_web\\resources\\control.ctl"));
            String s = null;
            while ((s = file1.readLine()) != null)
            {
                s = s.replace("TELPHONE", fileName).replace("TABLE", tableName);
                file2.write(s);
                file2.newLine();
            }
            file2.flush();
            file1.close();
            file2.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public static List<String> getFileNameList(String path){
    	 List<String> fileNameLists = new ArrayList<String>();
        // get file list where the path has   
        File file = new File(path);   
        // get the folder list   
        File[] array = file.listFiles();   
          if(array != null ){
        	  for(int i=0;i<array.length;i++){   
                  if(array[i].isFile()){   
                  	fileNameLists.add(array[i].getName());
                  }else if(array[i].isDirectory()){   
                  	getFileNameList(array[i].getPath());   
                  }   
              }  
          }
        
        return fileNameLists;
    }    
}