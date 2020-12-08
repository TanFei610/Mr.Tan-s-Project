package com.microtest.exception_handle_project.mapper;

import com.microtest.exception_handle_project.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUser(String username);

}
