package com.ly.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: parent
 * @Package: com.ly.config
 * @ClassName: FtpConfig
 * @Author: lin
 * @Description: FTP配置信息
 * @Date: 2019-11-25 9:18
 * @Version: 1.0
 */
@Configuration
public class FtpConfig {
    @Value("${ftp.ip}")
    private String ip;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.name}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.path}")
    private String path;
    @Value("${ftp.url}")
    private String url;

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

    public String getUrl() {
        return url;
    }
}
