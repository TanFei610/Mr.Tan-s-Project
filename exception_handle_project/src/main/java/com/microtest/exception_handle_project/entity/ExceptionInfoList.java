package com.microtest.exception_handle_project.entity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
//<th class="active" style="text-align: center">日期</th>
//<th class="success" style="text-align: center">客户</th>
//<th class="warning" style="text-align: center">项目号</th>
//<th class="danger" style="text-align: center">项目名称</th>
//<th class="info" style="text-align: center">阶段</th>
//<th class="info" style="text-align: center">数量</th>
//<th class="success" style="text-align: center">设计负责人</th>
//<th class="success" style="text-align: center">问题分析</th>
//<th class="success" style="text-align: center">问题等级</th>
//<th class="success" style="text-align: center">问题反馈</th>
//<th class="success" style="text-align: center">原因分析</th>
//<th class="success" style="text-align: center">解决进度</th>
//<th class="success" style="text-align: center">状态</th>
//<th class="success" style="text-align: center">备注</th>
//<th class="success" style="text-align: center">操作</th>
@Repository
public class ExceptionInfoList {
    private static ArrayList<String> exceptionInfoList = null;
    static {
        exceptionInfoList = new ArrayList<String>();
//        exceptionInfoList.add("ID");
        exceptionInfoList.add("日期");
        exceptionInfoList.add("客户");
        exceptionInfoList.add("项目号");
        exceptionInfoList.add("项目名称");
        exceptionInfoList.add("阶段");
        exceptionInfoList.add("数量");
        exceptionInfoList.add("设计负责人");
        exceptionInfoList.add("问题分类");
        exceptionInfoList.add("问题等级");
        exceptionInfoList.add("问题反馈");
        exceptionInfoList.add("原因分析");
        exceptionInfoList.add("解决进度");
        exceptionInfoList.add("DRI");
        exceptionInfoList.add("状态");
        exceptionInfoList.add("备注");
        exceptionInfoList.add("图片备注");
        exceptionInfoList.add("操作");

    }
    public static ArrayList<String> getExceptionInfoList(){
        return exceptionInfoList;
    }


}
