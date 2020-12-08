package com.microtest.exception_handle_project.controller;

import com.microtest.exception_handle_project.dao.ExceptionNumber;
import com.microtest.exception_handle_project.entity.User;
import com.microtest.exception_handle_project.mapper.ExceptionListNumberMapper;
import com.microtest.exception_handle_project.mapper.UserMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class LoginController {
    @Resource
    UserMapper userMapper;

    @RequestMapping("/login")
    public String login(String username, String password, ServletRequest servletRequest, HttpSession session) {
        User user = userMapper.selectUser(username);
        if (!StringUtils.isEmpty(user) && password.equals(user.getPassword())) {
            session.setAttribute("loginUser", username);
            servletRequest.removeAttribute("msg");
            return "redirect:/main1";
        } else {
            servletRequest.setAttribute("msg", "账号或者密码错误");
            return "index";
        }
    }
    @RequestMapping("/exit")
    public String loginExit(HttpSession session) {
        session.removeAttribute("loginUser");
        return "forward:/";
    }
}
