package com.tuxzx.controller;

import com.sun.istack.internal.Nullable;
import com.tuxzx.service.UserService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    //todo 完成BookController的service层逻辑

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getBookByType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showBookByType (@Nullable int type) {
        return StringUtils.mapToJson(userService.getAllBookByTypeWithHtml(type));
    }

    @RequestMapping(value = "getBookAllType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showBookAllType () {
        return StringUtils.mapToJson(userService.getAllBookTypeWithHtml());
    }

    @RequestMapping(value = "getBookInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getBookInfo(String isbn){
        return new ModelAndView("bookinfo",userService.getBookInfo(isbn));
    }
}
