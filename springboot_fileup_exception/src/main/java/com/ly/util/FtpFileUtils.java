package com.ly.util;

import com.ly.config.FtpConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ProjectName: parent
 * @Package: com.ly.util
 * @ClassName: FtpFileUtils
 * @Author: lin
 * @Description: FTP
 * @Date: 2019-11-21 16:04
 * @Version: 1.0
 */
public class FtpFileUtils {

    public static String upload(FtpConfig config, MultipartFile file) {
        String oldName = file.getOriginalFilename ();
        String newName = UUID.randomUUID ().toString ().replaceAll ("-", "") + oldName.substring (oldName.lastIndexOf ("."));
        FTPClient client = new FTPClient ();
        boolean isSuccess = false;
        try {
            client.connect (config.getIp (), config.getPort ());
            boolean login = client.login (config.getUsername (), config.getPassword ());
            if (!login) {
                // 断开连接
                client.disconnect ();
                return "登录失败";
            }
            // 切换到文件上传路径
            boolean b = client.changeWorkingDirectory (config.getPath ());
            System.out.println (b);
            // 设置文件为二进制类型
            client.setFileType (FTP.BINARY_FILE_TYPE);
            isSuccess = client.storeFile (newName, file.getInputStream ());
            // OutputStream stream = client.storeFileStream (newName);

        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                client.logout ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            try {
                client.disconnect ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return isSuccess ? config.getUrl () + newName : "上传失败";
    }

    public static String download(FtpConfig config, String filename, HttpServletResponse response) {
        FTPClient client = new FTPClient ();
        boolean isSuccess = false;
        try {
            client.connect (config.getIp (), config.getPort ());
            boolean login = client.login (config.getUsername (), config.getPassword ());
            if (!login) {
                // 断开连接
                client.disconnect ();
                return "登录失败";
            }
            client.changeWorkingDirectory (config.getPath ());
            isSuccess = client.retrieveFile (filename, response.getOutputStream ());
        } catch (IOException e) {
            e.printStackTrace ();
        }finally {
            try {
                client.logout ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            try {
                client.disconnect ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return isSuccess ? "文件下载成功" : "文件下载失败";
    }


    public static void main(String[] args) {
        FTPClient client = new FTPClient ();
        try {
            client.connect ("192.168.16.177", 21);
            boolean flank = client.login ("flank", "123");
            System.out.println (flank ? "成功" : "失败");
            boolean b = client.changeWorkingDirectory ("/home/virtual/flank/upload/");
            System.out.println (b);
            boolean b1 = client.makeDirectory ("a");
            System.out.println (b1);
            client.disconnect ();
            InputStream stream = client.retrieveFileStream ("");
            byte[] bytes = new byte[10240];
            int read = stream.read (bytes);

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
