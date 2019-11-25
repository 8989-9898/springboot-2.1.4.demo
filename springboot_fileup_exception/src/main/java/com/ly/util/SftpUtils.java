package com.ly.util;

import com.jcraft.jsch.*;
import com.ly.config.SftpConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ProjectName: parent
 * @Package: com.ly.util
 * @ClassName: SftpUtils
 * @Author: lin
 * @Description: sftp 协议工具类
 * @Date: 2019-11-25 14:12
 * @Version: 1.0
 */
public class SftpUtils {
    private static ChannelSftp connect(SftpConfig config) throws JSchException {
        JSch sch = new JSch ();
        // 获取 sftp 服务器连接会话
        Session session = sch.getSession (config.getUsername (), config.getIp (), config.getPort ());
        // 设置连接服务器的密码
        session.setPassword (config.getPassword ());
        // 设置StrictHostKeyChecking 键的值， 为 yes ，ssh将不会自动将计算机的密钥加入 "$HOST/.ssh/known_hosts" 文件中，
        // 一旦密钥发生改变将不能连接 sftp 服务器
        Properties properties = new Properties ();
        properties.setProperty ("StrictHostKeyChecking","no");
        // 设置会话参数
        session.setConfig (properties);
        // 连接
        session.connect ();
        // 打开一个连接通道
        ChannelSftp sftp = (ChannelSftp)session.openChannel ("sftp");
        // 打开通道连接
        sftp.connect ();

        return sftp;
    }

    public static String upload(InputStream stream, SftpConfig config, String newName){
        ChannelSftp sftp=null;
        try {
            sftp = connect (config);
            // 进行上传
            sftp.put (stream,config.getPath ()+newName);
        } catch (JSchException e) {
            e.printStackTrace ();
        } catch (SftpException e) {
            e.printStackTrace ();
        }finally {
            sftp.disconnect ();
        }
        return "上传成功";
    }

    public static String download(SftpConfig config, HttpServletResponse response, String filename){
        ChannelSftp sftp=null;
        try {
            sftp = connect (config);
            // 进行下载
            sftp.get(config.getPath ()+filename,response.getOutputStream ());
        } catch (JSchException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (SftpException e) {
            e.printStackTrace ();
        } finally {
            sftp.disconnect ();
        }
        return "下载成功";
    }
}
