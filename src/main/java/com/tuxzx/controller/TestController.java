package com.tuxzx.controller;

import com.tuxzx.dal.impl.BorrowDaoImpl;
import com.tuxzx.domain.BorrowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    BorrowDaoImpl borrowDao = new BorrowDaoImpl();

    @GetMapping("/test")
    @ResponseBody
    public String toTest(HttpSession session) {
       return (String) session.getAttribute("username");
    }
}
