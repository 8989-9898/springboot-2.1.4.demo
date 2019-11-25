package com.ly.service.impl;

import com.ly.config.FtpConfig;
import com.ly.service.FtpService;
import com.ly.util.FtpFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: parent
 * @Package: com.ly.service.impl
 * @ClassName: FtpServiceImpl
 * @Author: lin
 * @Description: ftp文件上传实现
 * @Date: 2019-11-25 9:42
 * @Version: 1.0
 */
@Service
public class FtpServiceImpl implements FtpService {

    @Autowired
    private FtpConfig ftpConfig;
    @Override
    public String upload(MultipartFile file) {
        String upload = FtpFileUtils.upload (ftpConfig, file);
        return upload;
    }

    @Override
    public void download(HttpServletResponse response) {
        String download = FtpFileUtils.download (ftpConfig, "timg.jpg", response);
        System.out.println (download);
    }
}
