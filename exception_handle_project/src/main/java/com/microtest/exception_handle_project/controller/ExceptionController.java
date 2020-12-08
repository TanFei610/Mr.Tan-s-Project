package com.microtest.exception_handle_project.controller;

import com.microtest.exception_handle_project.entity.ExceptionHandleList;
import com.microtest.exception_handle_project.mapper.ExceptionInfoListMapper;
import com.microtest.exception_handle_project.util.ImageHandle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SpringBootApplication
@Controller
public class ExceptionController {
    @Resource
    ExceptionInfoListMapper exceptionInfoListMapper;
    //增加异常或者修改异常
    ImageHandle imageHandle = new ImageHandle();
    @RequestMapping("/exception")
    public String addException(ExceptionHandleList exceptionHandleList, MultipartFile imageUrl) throws IOException {
        //加载图片到指定文件夹并返回路径
            String realPath=null;
        if (!imageUrl.isEmpty()){
            realPath = new ImageHandle().imageSave(imageUrl);
        }
        //设置文件路径
        exceptionHandleList.setRealPath(realPath);
        if (exceptionHandleList.getId()==0){
            exceptionInfoListMapper.addException(exceptionHandleList);
        }
        else{
                String realPath1 = exceptionInfoListMapper.selectRealPath(exceptionHandleList.getId());
                if(realPath!=null){
                    imageHandle.delImage(realPath1);
                }
            exceptionInfoListMapper.Modify(exceptionHandleList);
        }
        return "redirect:/main1";
    }

    //删除异常
    @RequestMapping("/deleteException{id}")
    public String deleteException(@PathVariable("id") Integer id){
        String realPath = exceptionInfoListMapper.selectRealPath(id);
        if(imageHandle.delImage(realPath)){
            exceptionInfoListMapper.deleteException(id);
        }

        return "redirect:/main1";
    }

    //修改异常
    @RequestMapping("/modify{id}")
    public String modified(@PathVariable int id, Model model){
        ExceptionHandleList exceptionInfoList = exceptionInfoListMapper.getId(id);
        model.addAttribute("exceptionInfoList",exceptionInfoList);
        model.addAttribute("modify",true);
        return "addException";
    }

    @ResponseBody
    @RequestMapping("/changeState")
    public boolean changeState(HttpServletRequest request){
        String result = request.getParameter("info");
        String[] strings = result.split(",");
        int state = Integer.valueOf(strings[0]);
        String type = strings[1];
        int id = Integer.valueOf(request.getParameter("id"));
        exceptionInfoListMapper.changeState(type,state,id);
        return true;
    }

}
