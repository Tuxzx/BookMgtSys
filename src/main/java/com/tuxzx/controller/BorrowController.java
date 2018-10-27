package com.tuxzx.controller;

import com.tuxzx.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/getBorrowedBook")
    public ModelAndView getBorrowedBook(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedBook((String) session.getAttribute("username")));
        return modelAndView;
    }

    @GetMapping("/getBorrowedBookHistory")
    public ModelAndView getBorrowedBookHistroy(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedBookHistory((String) session.getAttribute("username")));
        return modelAndView;
    }

    @GetMapping("/getBorrowedBookRanking")
    public ModelAndView getBorrowedBookRanking(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedRanking());
        return modelAndView;
    }
}
