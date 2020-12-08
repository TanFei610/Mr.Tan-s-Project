package com.microtest.exception_handle_project.entity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
//<option>日期</option>
//<option>问题状态</option>
//<option>解决进度</option>
//<option>项目号</option>
//<option>治具类型</option>
@Repository
public class SelectConditionList {
    private static ArrayList<String> selectConditionList = null;

    static {
        selectConditionList = new ArrayList<String>();
        selectConditionList.add("全部");
        selectConditionList.add("日期");
        selectConditionList.add("客户");
        selectConditionList.add("项目号");
        selectConditionList.add("项目名称");
        selectConditionList.add("设计负责人");
        selectConditionList.add("问题等级");
        selectConditionList.add("问题分类");
        selectConditionList.add("状态");
    }

    public static ArrayList<String> getSelectConditionList() {
        return selectConditionList;
    }

}
