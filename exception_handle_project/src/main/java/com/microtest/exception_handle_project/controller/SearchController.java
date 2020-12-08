package com.microtest.exception_handle_project.controller;


import com.microtest.exception_handle_project.entity.ExceptionInfoList;
import com.microtest.exception_handle_project.entity.SelectConditionList;
import com.microtest.exception_handle_project.mapper.ExceptionInfoListMapper;
import com.microtest.exception_handle_project.mapper.ExceptionListNumberMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class SearchController {

    @Resource
    ExceptionInfoListMapper exceptionInfoListMapper;
    @Resource
    ExceptionListNumberMapper exceptionListNumberMapper;
    ArrayList<String> selectConditionList = new SelectConditionList().getSelectConditionList();
    ArrayList<String> ExceptionInfoList = new ExceptionInfoList().getExceptionInfoList();

    @RequestMapping(value = "/search")
    public String search(String selectType, String info, Model model, HttpSession httpSession) {
        List e = null;
        int conditionSelectNum = 0;
        httpSession.removeAttribute("pageNum");
        model.addAttribute("selectConditionList", selectConditionList);
        model.addAttribute("ExceptionInfoList", ExceptionInfoList);
        switch (selectType) {

            case ("全部"):
                e = exceptionInfoListMapper.selectAll(info);
                conditionSelectNum = exceptionInfoListMapper.selectAllNum(info,null);
                break;

            case ("日期"):
                e = exceptionInfoListMapper.conditionSelect("date", null);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("date", info);
                break;
            case ("客户"):
                e = exceptionInfoListMapper.conditionSelect("customer", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("customer", info);
                break;
            case ("项目号"):
                e = exceptionInfoListMapper.conditionSelect("projectNumber", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("projectNumber", info);
                break;

            case ("项目名称"):
                e = exceptionInfoListMapper.conditionSelect("projectName", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("projectName", info);
                break;

            case ("设计负责人"):
                e = exceptionInfoListMapper.conditionSelect("charge", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("charge", info);

                break;


            case ("问题等级"):
                e = exceptionInfoListMapper.specialConditionSelect("exceptionRank","exceptionRank", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("exceptionRank", info);
                break;
            case ("问题分类"):
                e = exceptionInfoListMapper.specialConditionSelect("exceptionClassify","exceptionClassify", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("exceptionClassify", info);
                break;

            case ("状态"):
                System.out.println(info);
                e = exceptionInfoListMapper.specialConditionSelect("exceptionHandState","State", info);
                conditionSelectNum = exceptionInfoListMapper.conditionSelectNum("State", info);
                break;

            default:
                break;

        }
        model.addAttribute("searchNum", conditionSelectNum);
        model.addAttribute("ExceptionHandleList", e);


        return "main";
    }
}
