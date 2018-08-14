package com.eqy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @ClassName: FtpUtil
 * @Description: FTP上传下载工具类
 * @author zz
 * @date 2016-7-13 下午5:02:36
 */
public class FTPUtil
{
    /**
     * @ClassName: FTPStatus
     * @Description: ftp工具类变量
     * @author zz
     * @date 2016-8-1 下午8:39:27
     */
    public enum FTPStatus {
        File_Exits(0), Create_Directory_Success(1), Create_Directory_Fail(2), Upload_From_Break_Success(
            3), Upload_From_Break_Faild(4), Download_From_Break_Success(5), Download_From_Break_Faild(
            6), Upload_New_File_Success(7), Upload_New_File_Failed(8), Delete_Remote_Success(9), Delete_Remote_Faild(
            10), Remote_Bigger_Local(11), Remote_smaller_local(12), Not_Exist_File(13), Remote_Rename_Success(
            14), Remote_Rename_Faild(15), File_Not_Unique(16);
        private int status;

        public int getStatus()
        {
            return status;
        }
        public void setStatus(int status)
        {
            this.status = status;
        }
        FTPStatus(int status)
        {
            this.status = status;
        }
    }

    private FTPClient ftpClient = new FTPClient();

    /**
     * @Title: createDir
     * @Description: 创建FTP服务文件夹
     * @param url
     *            图片存放的文件夹路径
     */
    public void createDir(String url)
    {
        try
        {
            ftpClient.makeDirectory(url);
            System.out.println("在目标服务器上成功建立了文件夹: " + url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @Title: uploadFile
     * @Description: 向FTP服务器上传文件
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param input
     *            输入流
     * @return 成功返回true，否则返回false
     */
    public boolean uploadFile(String url, int port, String username, String password, String path,
                              String filename, InputStream input)
    {
        // 初始表示上传失败
        boolean success = false;
        try
        {
            int reply;
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.connect(url, port);
            // 登录ftp
            ftpClient.login(username, password);
            // 看返回的值是不是230，如果是，表示登陆成功
            reply = ftpClient.getReplyCode();
            // 以2开头的返回值就会为真
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftpClient.disconnect();
                return success;
            }
            // 设置上传文件的类型为二进制类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 转到指定上传目录
            ftpClient.changeWorkingDirectory(path);
            // 将上传文件存储到指定目录
            ftpClient.storeFile(filename, input);
            // 关闭输入流
            input.close();
            // 退出ftp
            ftpClient.logout();
            // 表示上传成功
            success = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftpClient.isConnected())
            {
                try
                {
                    ftpClient.disconnect();
                }
                catch (IOException ioe)
                {}
            }
        }
        return success;
    }
    /**
     * Description: 从FTP服务器下载文件
     * 
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String url, int port, String username, String password,
                            String remotePath, String fileName, String localPath)
    {
        // 初始表示下载失败
        boolean success = false;
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try
        {
            int reply;
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.connect(url, port);
            // 登录ftp
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                return success;
            } // 转到指定下载目录
            ftp.changeWorkingDirectory(remotePath);
            // 列出该目录下所有文件
            FTPFile[] fs = ftp.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile ff : fs)
            {
                if (ff.getName().equals(fileName))
                {
                    // 根据绝对路径初始化文件
                    File localFile = new File(localPath + "/" + ff.getName());
                    // 输出流
                    OutputStream is = new FileOutputStream(localFile);
                    // 下载文件
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            // 退出ftp
            ftp.logout();
            // 下载成功
            success = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {}
            }
        }
        return success;
    }
    /**
     * @Title: delete
     * @Description: 删除远程FTP文件
     * @param remote
     *            远程文件路径
     * @return
     * @throws IOException
     */
    public FTPStatus delete(String remote) throws IOException
    {
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPStatus result = null;
        FTPFile[] files = ftpClient.listFiles(remote);
        if (files.length == 1)
        {
            boolean status = ftpClient.deleteFile(remote);
            result = status ? FTPStatus.Delete_Remote_Success : FTPStatus.Delete_Remote_Faild;
        }
        else
        {
            result = FTPStatus.Not_Exist_File;
        }
        return result;
    }
    /**
     * @Title: rename
     * @Description: 重命名远程FTP文件
     * @param name
     *            新远程文件名称 (路径-必须保证在同一路径下)
     * @param remote
     *            远程文件路径
     * @return 是否成功
     * @throws IOException
     */
    public FTPStatus rename(String remote, String name) throws IOException
    {
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPStatus result = null;
        FTPFile[] files = ftpClient.listFiles(remote);
        if (files.length == 1)
        {
            boolean status = ftpClient.rename(remote, name);
            result = status ? FTPStatus.Remote_Rename_Success : FTPStatus.Remote_Rename_Faild;
        }
        else
        {
            result = FTPStatus.Not_Exist_File;
        }
        return result;
    }
    /**
     * @Title: connect
     * @Description: 连接ftp服务
     * @param hostname
     *            主机名
     * @param port
     *            端口
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 是否连接成功
     * @throws IOException
     */
    public boolean
        connect(String hostname, int port, String username, String password) throws IOException
    {
        ftpClient.connect(hostname, port);
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
        {
            if (ftpClient.login(username, password))
            {
                return true;
            }
        }
        disconnect();
        return false;
    }
    /**
     * @Title: download
     * @Description: 从FTP服务器上下载文件
     * @param fileName
     *            下载文件的名字(包括后缀名)
     * @param remote
     *            远程文件路径
     * @param response
     * @return 是否成功
     * @throws IOException
     */
    public FTPStatus
        download(String fileName, String remote, HttpServletResponse response) throws IOException
    {
        // 开启输出流弹出文件保存路径选择窗口
        response.setContentType("application/octet-stream");
        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPStatus result;
        OutputStream out = response.getOutputStream();
        boolean status = ftpClient.retrieveFile(remote, out);
        result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
        out.close();
        return result;
    }
    /**
     * @Title: download
     * @Description: 从FTP服务器上下载文件
     * @param remote
     *            远程文件路径
     * @param local
     *            本地文件路径
     * @return 是否成功
     * @throws IOException
     */
    public FTPStatus download(String remote, String local) throws IOException
    {
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPStatus result;
        File f = new File(local);
        FTPFile[] files = ftpClient.listFiles(remote);
        if (files.length != 1)
        {
            // Log.getLogger(this.getClass()).info("远程文件不唯一");
            return FTPStatus.File_Not_Unique;
        }
        long lRemoteSize = files[0].getSize();
        if (f.exists())
        {
            OutputStream out = new FileOutputStream(f, true);
            if (f.length() >= lRemoteSize)
            {
                return FTPStatus.Remote_smaller_local;
            }
            ftpClient.setRestartOffset(f.length());
            boolean status = ftpClient.retrieveFile(remote, out);
            result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
            out.close();
        }
        else
        {
            OutputStream out = new FileOutputStream(f);
            boolean status = ftpClient.retrieveFile(remote, out);
            result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
            out.close();
        }
        return result;
    }
    /**
     * @Title: upload
     * @Description: 上传文件到FTP服务器，支持断点续传
     * @param local
     *            本地文件名称，绝对路径
     * @param remote
     *            远程文件路径，使用/home/directory1/subdirectory/file.ext,
     *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @return 上传结果
     * @throws IOException
     */
    public FTPStatus upload(String local, String remote) throws IOException
    {
        // 设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        // 设置以二进制流的方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPStatus result;
        // 对远程目录的处理
        String remoteFileName = remote;
        if (remote.contains("/"))
        {
            remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
            String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
            if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory))
            {
                // 如果远程目录不存在，则递归创建远程服务器目录
                int start = 0;
                int end = 0;
                if (directory.startsWith("/"))
                {
                    start = 1;
                }
                else
                {
                    start = 0;
                }
                end = directory.indexOf("/", start);
                while (true)
                {
                    String subDirectory = remote.substring(start, end);
                    if (!ftpClient.changeWorkingDirectory(subDirectory))
                    {
                        if (ftpClient.makeDirectory(subDirectory))
                        {
                            ftpClient.changeWorkingDirectory(subDirectory);
                        }
                        else
                        {
                            return FTPStatus.Create_Directory_Fail;
                        }
                    }
                    start = end + 1;
                    end = directory.indexOf("/", start);
                    // 检查所有目录是否创建完毕
                    if (end <= start)
                    {
                        break;
                    }
                }
            }
        }
        // 检查远程是否存在文件
        FTPFile[] files = ftpClient.listFiles(remoteFileName);
        if (files.length == 1)
        {
            long remoteSize = files[0].getSize();
            File f = new File(local);
            long localSize = f.length();
            if (remoteSize == localSize)
            {
                return FTPStatus.File_Exits;
            }
            else if (remoteSize > localSize)
            {
                return FTPStatus.Remote_Bigger_Local;
            }
            // 尝试移动文件内读取指针,实现断点续传
            InputStream is = new FileInputStream(f);
            if (is.skip(remoteSize) == remoteSize)
            {
                ftpClient.setRestartOffset(remoteSize);
                if (ftpClient.storeFile(remote, is))
                {
                    return FTPStatus.Upload_From_Break_Success;
                }
            }
            // 如果断点续传没有成功，则删除服务器上文件，重新上传
            if (!ftpClient.deleteFile(remoteFileName))
            {
                return FTPStatus.Delete_Remote_Faild;
            }
            is = new FileInputStream(f);
            if (ftpClient.storeFile(remote, is))
            {
                result = FTPStatus.Upload_New_File_Success;
            }
            else
            {
                result = FTPStatus.Upload_New_File_Failed;
            }
            is.close();
        }
        else
        {
            InputStream is = new FileInputStream(local);
            if (ftpClient.storeFile(remoteFileName, is))
            {
                result = FTPStatus.Upload_New_File_Success;
            }
            else
            {
                result = FTPStatus.Upload_New_File_Failed;
            }
            is.close();
        }
        return result;
    }
    /**
     * @Title: disconnect
     * @Description: 断开与远程服务器的连接
     * @throws IOException
     */
    public void disconnect() throws IOException
    {
        if (ftpClient.isConnected())
        {
            ftpClient.disconnect();
        }
    }
    /**
     * @Title: FTPUtil
     * @Description: 对象构造 设置将过程中使用到的命令输出到控制台
     */
    public FTPUtil()
    {
        this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(
            System.out)));
    }
}
