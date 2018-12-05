package com.tuxzx.controller;

import com.tuxzx.dal.BookDao;
import com.tuxzx.dal.impl.BorrowDaoImpl;
import com.tuxzx.domain.BorrowInfo;
import com.tuxzx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    BookService bookService;
    @Autowired
    BookDao bookDao;


}
