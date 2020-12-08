package com.microtest.exception_handle_project.util;

import com.microtest.exception_handle_project.mapper.ExceptionInfoListMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;


public class ImageHandle {

    @Resource
    ExceptionInfoListMapper exceptionInfoListMapper;

    public String imageSave(MultipartFile multipartFile) throws IOException {
        String realPath = null;
        if(multipartFile!=null){
            //定义文件夹
            String savePath = "C:\\Users\\etc01\\Desktop\\testFile/";
            //定义文件名称
            String fileName = "save" + new Date().getTime();
            //获取文件类型
            String fileType ="."+multipartFile.getContentType().split("/")[1];
            //定义文件及路径到mysql
            realPath = savePath+fileName+fileType;
            multipartFile.transferTo(new File(realPath));
        }
        return realPath;
    }

    public byte[] imageGet(String realPath) throws IOException {
        //定义文件
        if(realPath!=null){
            File file = new File(realPath);
            //初始化字节
            byte b[] = null;
            //定义文件流
            FileInputStream fileInputStream = null;
            try{
                //赋值
                fileInputStream = new FileInputStream(file);
                b = fileInputStream.readAllBytes();
            }
            catch (FileNotFoundException e){
                //打印异常信息
                e.getMessage();
            }
            finally {
                //关闭流
                if(fileInputStream!=null){
                    fileInputStream.close();
                }

            }
            return b;
        }
        return null;
        }


    public boolean delImage(String realPath) {
        File img = null;
        if(realPath!=null){
            img = new File(realPath);
            if (img.exists()){
                img.delete();
            }
            return true;
        }
        return false;
    }
}
