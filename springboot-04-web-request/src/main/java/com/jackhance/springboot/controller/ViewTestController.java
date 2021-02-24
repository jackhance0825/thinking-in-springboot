package com.jackhance.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewTestController {

    @GetMapping("/viewTest")
    public String atguigu(Model model, HttpServletResponse response){

        //model中的数据会被放在请求域中 request.setAttribute("a",aa)
        model.addAttribute("msg","你好 jackhance");
        model.addAttribute("link","http://www.baidu.com");

        response.setContentType("application/xhtml");
        return "success";
    }
}
