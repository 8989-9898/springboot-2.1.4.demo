package com.ly.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: parent
 * @Package: com.ly.service
 * @ClassName: SftpService
 * @Author: lin
 * @Description: sftp 文件上传下载业务层接口
 * @Date: 2019-11-25 14:07
 * @Version: 1.0
 */
public interface SftpService {

    String upload(MultipartFile file);

    String download(HttpServletResponse response);
}
