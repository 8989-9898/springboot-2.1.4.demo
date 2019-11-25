package com.ly.controller;

import com.ly.service.SftpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ProjectName: parent
 * @Package: com.ly.controller
 * @ClassName: SftpController
 * @Author: lin
 * @Description:
 * @Date: 2019-11-25 14:46
 * @Version: 1.0
 */
@Controller
public class SftpController {

    @Autowired
    private SftpService sftpService;

    @PostMapping("/sftpUpload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file){
        String upload = sftpService.upload (file);
        return new ModelAndView ("upload","fileMessage",upload);
    }

    @GetMapping("/sftpDownload")
    public void download(HttpServletResponse response){
        response.setHeader ("content-type","application/octet-stream");
        response.setContentType ("application/force-download");
        try {
            response.setHeader ("content-disposition","attachment;filename="+ URLEncoder.encode ("test.jpg","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        String download = sftpService.download (response);
        System.out.println (download);
    }
}
