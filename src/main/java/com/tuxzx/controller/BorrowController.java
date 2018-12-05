package com.tuxzx.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tuxzx.service.BorrowService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequestMapping("/user")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/toBorrowedBook")
    public ModelAndView getBorrowedBook(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedBook((String) session.getAttribute("username")));
        return modelAndView;
    }

    @GetMapping("/toBorrowedBookHistory")
    public ModelAndView getBorrowedBookHistroy(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedBookHistory((String) session.getAttribute("username")));
        return modelAndView;
    }

    @GetMapping("/toBorrowedBookRanking")
    public ModelAndView getBorrowedBookRanking(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", borrowService.getBorrowedRanking());
        return modelAndView;
    }

    @GetMapping("/toBorrowAndReturn")
    public String toBorrowAndReturn () {
        return "management_BorrowAndReturnMgt";
    }

    @GetMapping("/toBorrowAndReturnHistory")
    public ModelAndView toBorrowAndReturnHistory () {
        ModelAndView modelAndView = new ModelAndView("management_BorrowAndReturnHistoryMgt");
        modelAndView.addObject("content", borrowService.getBorrowedBookHistoryAll());
        return modelAndView;
    }

    @PostMapping(value = "postBorrow", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postBorrow(String stu_id, String book_isbn) {
        if (!borrowService.isStudentExist(stu_id) || !borrowService.isBookExist(book_isbn)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","用户id或书isbn不存在！\n请检查输入！"));
        }
        Date nowDate = new Date(System.currentTimeMillis());
        // 借书
        long oneDay = 86400000;
        Date expectdate = new Date(System.currentTimeMillis()+ oneDay*45);
        if (borrowService.borrowBook(stu_id, book_isbn, nowDate, expectdate)){
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success","借阅成功"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","借阅失败，系统出现错误！"));
        }
    }


    @PostMapping(value = "postReturn", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postReturn(int borrow_id, String stu_id, String book_isbn) {
        if (borrowService.isReturned(borrow_id)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","该书已还！\n请勿重复提交！"));
        }
        if (!borrowService.isBorrowExist(borrow_id)|| !borrowService.isStudentExist(stu_id) || !borrowService.isBookExist(book_isbn)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","借阅号、用户id、书isbn有误！\n请检查输入！"));
        }
        Date nowDate = new Date(System.currentTimeMillis());
        if (borrowService.returnBook(stu_id, borrow_id, book_isbn, nowDate)){
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success","还书成功"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","还书失败，系统出现错误！"));
        }
    }
}
