package com.microtest.exception_handle_project.dao;

import com.microtest.exception_handle_project.mapper.ExceptionListNumberMapper;

import javax.annotation.Resource;

public class ExceptionNumber {
    @Resource
   ExceptionListNumberMapper exceptionListNumberMapper;

    public Integer getExceptionListNumber(){
        return exceptionListNumberMapper.exceptionListNumber();
    }
}
