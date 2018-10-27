package com.tuxzx.controller;

import com.tuxzx.service.LoginService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/")
    public String toIndex(HttpSession session){
        if (session.getAttribute("username")==null) {
            return "index";
        }else{
            return "redirect:/login";
        }
    }

    // 登陆
    @PostMapping (value = {"/login"}, produces = "text/html;charset=UTF-8")
    public ModelAndView toLogin(String username, String password, String role, HttpSession session) {
        ModelAndView modelAndView = null;
        Map map = loginService.login(username, password, role);
        if (map==null) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("message", "你好像走丢了,看看其他的内容吧！");
            return modelAndView;
        }
        modelAndView = new ModelAndView("home");
        modelAndView.addAllObjects(map);
        session.setAttribute("username", username);
        session.setAttribute("nickname", map.get("username"));
        session.setAttribute("role",role);
        return modelAndView;
    }

    // 登陆 判断session
    @GetMapping (value = {"/login"}, produces = "text/html;charset=UTF-8")
    public ModelAndView toLogin(HttpSession session) {
        ModelAndView modelAndView = null;
        if (session.getAttribute("username") == null) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("message","您暂未登陆，点击按钮到登陆页面！");
        }else {
            modelAndView = new ModelAndView("home");
            modelAndView.addAllObjects(loginService.loginAdmin((String) session.getAttribute("username"), (String) session.getAttribute("role")));
        }
        return modelAndView;
    }

    // ajax 动态校验
    @PostMapping(value = "/accountCheck", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String toHome(String username, String password, String role) {
        Map map = loginService.loginCheckAjax(username, password, role);
        return StringUtils.mapToJson(map);
    }
}
