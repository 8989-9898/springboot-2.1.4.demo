package com.ly.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: parent
 * @Package: com.ly.config
 * @ClassName: SftpConfig
 * @Author: lin
 * @Description: 使用 sftp 协议进行文件上传下载的配置类
 * @Date: 2019-11-25 14:02
 * @Version: 1.0
 */
@Configuration
public class SftpConfig {

    @Value("${sftp.ip}")
    private String ip;
    @Value("${sftp.port}")
    private Integer port;
    @Value("${sftp.name}")
    private String username;
    @Value("${sftp.password}")
    private String password;
    @Value("${sftp.path}")
    private String path;

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }
}
