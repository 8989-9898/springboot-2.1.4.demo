package com.ly.controller;

import com.ly.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ProjectName: parent
 * @Package: com.ly.controller
 * @ClassName: FtpController
 * @Author: lin
 * @Description: ftp文件上传下载控制层
 * @Date: 2019-11-25 10:38
 * @Version: 1.0
 */
@Controller
@RequestMapping("/ftp")
public class FtpController {

    @Autowired
    private FtpService ftpService;

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file){
        String upload = ftpService.upload (file);
        return new ModelAndView ("/upload","fileMessage",upload);
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response){
        response.setHeader ("content-type","application/octet-stream");
        response.setContentType ("application/force-download");
        try {
            response.setHeader ("content-disposition","attachment;filename="+ URLEncoder.encode ("timg.jpg","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        ftpService.download (response);
    }
}
