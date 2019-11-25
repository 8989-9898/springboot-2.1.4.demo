package com.ly.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: parent
 * @Package: com.ly.service
 * @ClassName: FtpService
 * @Author: lin
 * @Description: FTP上传文件的业务层接口
 * @Date: 2019-11-25 9:39
 * @Version: 1.0
 */
public interface FtpService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 文件下载
     */
    void download(HttpServletResponse response);

}
