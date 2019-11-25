package com.ly.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @ProjectName: parent
 * @Package: com.ly.controller
 * @ClassName: UploadController
 * @Author: lin
 * @Description: 文件上传的接口
 * @Date: 2019-10-17 11:25
 * @Version: 1.0
 */
@Controller
public class UploadController {
    private final static String PATH = "e://file//";

    @PostMapping("fileUpload")
    public ModelAndView uploadImg(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty ()) {
            String oldName = file.getOriginalFilename ();
            System.out.println (oldName);
            String newName = UUID.randomUUID ().toString ().replaceAll ("_", "") + oldName.substring (oldName.lastIndexOf ("."));
            String name = file.getName ();
            System.out.println (name);
            try {
                BufferedOutputStream stream = new BufferedOutputStream (new FileOutputStream (new File (PATH + newName)));
                stream.write (file.getBytes ());
                stream.flush ();
                stream.close ();
                System.out.println (PATH + newName);
            } catch (FileNotFoundException e) {
                e.printStackTrace ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            return new ModelAndView ("upload", "fileMessage", "图片上传成功");
        } else {
            return new ModelAndView ("upload", "fileMessage", "没有文件信息");
        }
    }

    @GetMapping("fileDownload")
    public String download(HttpServletResponse response) {
        //File path = null;
        // FileSystemResource resource = new FileSystemResource (new File (""));
        response.setHeader ("content-type", "application/octet-stream");
        // 设置不打开强制下载
        response.setContentType ("application/force-download");
        try {
            // 使用URLEncoder.encode ("","UTF-8"); 防止文件中文乱码
            response.setHeader ("content-disposition", "attachment;filename=" + URLEncoder.encode ("news.mp4", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        byte[] bytes = new byte[1024];
        BufferedInputStream in = null;
        OutputStream out = null;
        try {
            //path = new File (ResourceUtils.getURL ("classpath:").getPath ());
            //System.out.println (path.getAbsolutePath ());
            out = response.getOutputStream ();
            in = new BufferedInputStream (new FileInputStream (new File (PATH + "news.mp4")));
            int len = in.read (bytes);
            System.out.println (new String (bytes, 0, bytes.length, "UTF-8"));
            while (len != -1) {
                out.write (bytes, 0, bytes.length);
                out.flush ();
                len = in.read (bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (in != null) {
                try {
                    in.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
            if (out != null) {
                try {
                    out.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        return "upload";
    }

    @GetMapping("fileDownload2")
    public ResponseEntity<FileSystemResource> download2() {
        return ResponseEntity.ok (new FileSystemResource (PATH + "/changhong.mp4"));
    }


    @GetMapping("fileImg/{file}")
    public ResponseEntity<FileSystemResource> image(@PathVariable(required = false) String id, @PathVariable(required = false) String file) {
        System.out.println (id);
        System.out.println (file);
        return ResponseEntity.ok (new FileSystemResource (new File ("C:\\Picture\\1\\128\\2019-11-01\\"+file)));
    }


}
