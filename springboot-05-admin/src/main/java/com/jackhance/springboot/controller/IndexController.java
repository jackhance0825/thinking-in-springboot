package com.jackhance.springboot.controller;

import com.jackhance.springboot.error.UserTooManyException;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 测试拦截器
 */
@Controller
@Slf4j
public class IndexController {
    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "/html/login.html";
    }

    @PostMapping(value = {"/auth"})
    public String auth(String username, String password, HttpSession session) {
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        return "redirect:/content";
    }

    @RequestMapping(value = {"/content"})
    public String content(HttpSession session) {
        return "html/multipart.html";
    }

    @ResponseBody
    @RequestMapping("/testException")
    public String testException() {
        throw new UserTooManyException("User is too many!!!!");
    }
}
