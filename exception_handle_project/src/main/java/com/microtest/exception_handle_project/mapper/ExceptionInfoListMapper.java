package com.microtest.exception_handle_project.mapper;

import com.microtest.exception_handle_project.entity.ExceptionHandleList;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashSet;
import java.util.List;

@Mapper
public interface ExceptionInfoListMapper {
    List<ExceptionHandleList> selectExceptionHandleList();
    List<ExceptionHandleList> conditionSelect(String type ,String info);
    List<ExceptionHandleList> specialConditionSelect(String tableName,String type ,String info);
    List<ExceptionHandleList> selectAll(String info);
    void addException(ExceptionHandleList exceptionHandleList);
    void deleteException(int id);
    int conditionSelectNum(String type ,String info);
    int selectAllNum(String info,String type);
    List<ExceptionHandleList> selectFct(String type);
    List<ExceptionHandleList> selectIct(String type);
    List<ExceptionHandleList> selectAutomation(String type);
    ExceptionHandleList getId(int id);
    void Modify(ExceptionHandleList exceptionHandleList);
    String selectRealPath(int id);
    void changeState(String type,int state,int id);
    void excelExport(List<ExceptionHandleList> ExceptionHandleLists) ;
    List<String> selectExceptionInfo();
}
