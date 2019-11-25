package com.ly.service.impl;

import com.ly.config.SftpConfig;
import com.ly.service.SftpService;
import com.ly.util.SftpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ProjectName: parent
 * @Package: com.ly.service.impl
 * @ClassName: SftpServiceImpl
 * @Author: lin
 * @Description: sftp 文件上传下载实现
 * @Date: 2019-11-25 14:09
 * @Version: 1.0
 */
@Service
public class SftpServiceImpl implements SftpService {

    @Autowired
    private SftpConfig sftpConfig;

    @Override
    public String upload(MultipartFile file) {
        String oldName = file.getOriginalFilename ();
        String newName = UUID.randomUUID ().toString ().replaceAll ("-","")+oldName.substring (oldName.lastIndexOf ("."));
        String upload = null;
        try {
            upload = SftpUtils.upload (file.getInputStream (), sftpConfig, newName);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return upload;
    }

    @Override
    public String download(HttpServletResponse response) {
        String download = SftpUtils.download (sftpConfig, response, "79bf2fe07d7d454dbb5445f7d89a1011.jpg");
        return download;
    }
}
