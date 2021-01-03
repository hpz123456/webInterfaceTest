package com.markerhub.tool;


import cn.hutool.core.io.resource.InputStreamResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class fileOperation {

    public static final String separator = File.separator;

    /**
     * 下载样表
     *
     * @param filePath 文件上级目录
     * @param fileName 文件名
     * @param newName  下载的展示文件名
     * @return 响应
     */
    public static ResponseEntity<InputStreamResource> download(String filePath, String fileName, String newName) {
        String route = "static" + separator + "templates" + separator;
        String path = null;
        ResponseEntity<InputStreamResource> response = null;
        try {
            System.out.println("separator:" + separator);
            path = filePath + fileName;
            Resource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
            //File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition",
                    "attachment; filename="
                            + new String(newName.getBytes("gbk"), "iso8859-1") + ".xlsx");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            response = ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }



    private static void testFileDirOrName(String path) {
        File dirFile = new File(path);
        if (dirFile.exists()) {
            File[] files = dirFile.listFiles();
            if (files != null) {
                for (File fileChildDir : files) {
                    //输出文件名或者文件夹名
                    System.out.print(fileChildDir.getName());
                    if (fileChildDir.isDirectory()) {
                        System.out.println(" :  此为目录名");
                        //通过递归的方式,可以把目录中的所有文件全部遍历出来
                        testFileDirOrName(fileChildDir.getAbsolutePath());
                    }
                    if (fileChildDir.isFile()) {
                        System.out.println(" :  此为文件名");
                    }
                }
            }
        }else{
            System.out.println("你想查找的文件不存在");
        }
    }

}
