package com.ly.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: FileController
 * @Author: lin
 * @Description: 文件上传的控制层
 * @Date: 2019-05-14 15:51
 * @Version: 1.0
 */
@Controller
public class FileController {

    @GetMapping("/*")
    public String index() {
        return "index";
    }

    /**
     * 单个文件上传
     *
     * @param file 文件对象
     * @return 文件信息
     * @throws IOException
     */
    @PostMapping("fileUpSingle")
    @ResponseBody
    public Map<String, String> fileUp1(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("文件名：" + file.getOriginalFilename());
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件大小：" + file.getSize());

        file.transferTo(Paths.get("e://test/" + file.getOriginalFilename()));
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", file.getOriginalFilename());
        map.put("fileType", file.getContentType());
        map.put("fileSize", file.getSize() + "");
        return map;
    }


    /**
     * 多个文件上传
     *
     * @param files 文件对象
     * @return 文件信息
     * @throws IOException
     */
    @PostMapping("fileUpMultiple")
    @ResponseBody
    public ArrayList<Map<String, String>> fileUp2(@RequestParam("files") MultipartFile[] files) throws IOException {

        ArrayList<Map<String, String>> maps = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        if (null == files || files.length <= 0) {
            map.put("error", "文件对象为空");
            maps.add(map);
        } else {
            for (MultipartFile file : files) {
                System.out.println("文件名：" + file.getOriginalFilename());
                System.out.println("文件大小：" + file.getSize());
                System.out.println("文件类型：" + file.getContentType());
                // 将文件写入本地
                file.transferTo(Paths.get("e://test/" + file.getOriginalFilename()));
                map.put("fileName", file.getOriginalFilename());
                map.put("fileType", file.getContentType());
                map.put("fileSize", file.getSize() + "");
                maps.add(map);
            }
        }
        return maps;
    }


    /**
     * 使用base64 字节的方式上传图片
     *
     * @param base64
     * @return
     * @throws IOException
     */
    @PostMapping("fileBase")
    @ResponseBody
    public boolean fileup3(@RequestParam("base64") String base64) throws IOException {
        String filename = UUID.randomUUID().toString();
        File file = new File("e://test/" + filename + ".jpg");
        // 将图片的base64 字节分割
        String[] base64s = base64.split("base64");
        // 将分割的字符串转换为base64字节数组
        byte[] bytes = Base64.decodeBase64(base64s.length > 1 ? base64s[1] : base64s[0]);
        FileCopyUtils.copy(bytes, file);
        return file.exists();
    }
}
