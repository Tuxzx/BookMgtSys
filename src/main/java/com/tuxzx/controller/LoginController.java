package com.tuxzx.controller;

import com.tuxzx.service.LoginService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @RequestMapping(value = "/")
    public ModelAndView toIndex(){
        return new ModelAndView("index");
    }

    // 登陆
    @RequestMapping(value = {"login"}, produces = "text/html;charset=UTF-8")
    public ModelAndView toLogin(String username, String password, String role) {
        ModelAndView modelAndView = null;
        Map map = loginService.login(username, password, role);
        if (map==null) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("message", "你好像走丢了,看看其他的内容吧！");
            return modelAndView;
        }
        modelAndView = new ModelAndView("home");
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    // ajax 动态校验
    @RequestMapping(value = "accountCheck", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String toHome(String username, String password, String role) {
        Map map = loginService.loginCheckAjax(username, password, role);
        return StringUtils.mapToJson(map);
    }
}
