package com.microtest.exception_handle_project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microtest.exception_handle_project.entity.ExceptionInfoList;
import com.microtest.exception_handle_project.entity.SelectConditionList;
import com.microtest.exception_handle_project.mapper.ExceptionInfoListMapper;
import com.microtest.exception_handle_project.mapper.ExceptionListNumberMapper;
import com.microtest.exception_handle_project.util.ImageHandle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
@Controller
public class MainController {
    //mybatis接口,查询异常总数
    @Resource
    ExceptionListNumberMapper exceptionListNumberMapper;
    // //mybatis接口,查询异常info
    @Resource
    ExceptionInfoListMapper exceptionInfoListMapper;
    //获取查询type信息
    ArrayList<String> selectConditionList = new SelectConditionList().getSelectConditionList();
    //获取表头信息
    ArrayList<String> ExceptionInfoList = new ExceptionInfoList().getExceptionInfoList();
    List e;


    @RequestMapping({"/main{page}"})
    public String index(Model model, @PathVariable int page) throws IOException {

        model.addAttribute("pageNum", (exceptionListNumberMapper.exceptionListNumber() / 10) + 1);
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(page, 10);
        e = exceptionInfoListMapper.selectExceptionHandleList();
        model.addAttribute("ExceptionHandleList", e);
        model.addAttribute("selectConditionList", selectConditionList);
        model.addAttribute("ExceptionInfoList", ExceptionInfoList);
        model.addAttribute("isAll", true);
        //添加异常的总数到界面进行渲染
        return "main";
    }

    @ResponseBody
    @RequestMapping({"/main/image{id}"})
    public void loadImage(@PathVariable int id, Model model, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("gb2312");
        response.setHeader("Content-Type","image/jpeg");
        response.setContentType("text/html;charset=gb2312");
        ImageHandle imageHandle = new ImageHandle();
        byte[] bytes = imageHandle.imageGet(exceptionInfoListMapper.selectRealPath(id));
        if (bytes!=null){
            model.addAttribute("isImage", true);
            response.getOutputStream().write(bytes);
        }

    }

    @RequestMapping("/imageShow{id}")
    public String showImage(@PathVariable int id, HttpSession session) throws IOException {
        session.setAttribute("imageId",id);
        return "imageShow";
    }


    @ResponseBody
    @RequestMapping("/imageShow")
    public byte[] Image(HttpSession session) throws IOException {
        int id = (int)session.getAttribute("imageId");
        session.removeAttribute("imageId");
        ImageHandle imageHandle = new ImageHandle();
        return imageHandle.imageGet(exceptionInfoListMapper.selectRealPath(id));
    }


    @RequestMapping({"/main/{type}"})
    public String index(Model model, @PathVariable String type) {
        List e = null;
        switch (type) {
            case "fct":
                e = exceptionInfoListMapper.selectFct("FS");
                model.addAttribute("type", "fct");
                break;
            case "ict":
                e = exceptionInfoListMapper.selectIct("CA");
                model.addAttribute("type", "ict");
                break;
            case "automation":
                e = exceptionInfoListMapper.selectAutomation("FF");
                model.addAttribute("type", "automation");
                break;
            default:
                break;
        }
        model.addAttribute("selectConditionList", selectConditionList);
        model.addAttribute("ExceptionInfoList", ExceptionInfoList);
        model.addAttribute("ExceptionHandleList", e);
        return "main";
    }

}
