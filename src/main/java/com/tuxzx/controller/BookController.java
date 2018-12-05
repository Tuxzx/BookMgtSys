package com.tuxzx.controller;

import com.sun.istack.internal.Nullable;
import com.tuxzx.domain.Book;
import com.tuxzx.service.BookService;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 返回最新的20本书
     * @return
     */
    @GetMapping("/toNewerBookList")
    public ModelAndView getNewerBookList() {
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", bookService.getNewerBookInfo());
        return modelAndView;
    }

    @RequestMapping(value = "/getBookInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView getBookInfo(String isbn){
        return new ModelAndView("bookinfo",bookService.getBookInfo(isbn));
    }

    @RequestMapping(value = "/getBookByType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showBookByType (@Nullable int type) {
        return StringUtils.mapToJson(bookService.getAllBookByTypeWithHtml(type));
    }

    @RequestMapping(value = "/getBookAllType", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showBookAllType () {
        return StringUtils.mapToJson(bookService.getAllBookTypeWithHtml());
    }

    @PostMapping (value = "/getBookByISBN", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getBookInfoByISBN(String isbn) {
        String strHtml = bookService.getBookByISBN(isbn);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/getBookByName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getBookInfoByName(String name) {
        String strHtml = bookService.getBookByName(name);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/getBookByNameNav", produces = "text/html;charset=UTF-8")
    public ModelAndView getBookInfoByNameNav(String name) {
        String strHtml = bookService.getBookByNameNav(name);
        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject("content", strHtml);
        return modelAndView;
    }

    @PostMapping (value = "/getBookUpdateForm", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getBookUpdateForm(String isbn) {
        String strHtml = bookService.getUpdateBookForm(isbn);
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @GetMapping (value = "/getBookAddForm", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getBookAddForm() {
        String strHtml = bookService.getAddBookForm();
        Map map = null;
        if (strHtml == null) {
            map = StringUtils.returnToAjaxFormat("failed", "该书不存在，请检查输入");
        }else {
            map = StringUtils.returnToAjaxFormat("success", strHtml);
        }
        return StringUtils.mapToJson(map);
    }

    @PostMapping (value = "/updateBook", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateBook(String book_isbn, String book_name, String book_author, String book_pub, int book_count, Date book_intime, int book_type, String book_note) {
        Book book = new Book(book_isbn, book_name, book_author, book_pub, book_count, book_intime, book_type, book_note);
        if (bookService.updateBook(book)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success","更新成功!"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","更新失败!"));
        }
    }

    @PostMapping (value = "/addNewBook", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewBook(String book_isbn, String book_name, String book_author, String book_pub, int book_count, Date book_intime, int book_type, String book_note) {
        Book book = new Book(book_isbn, book_name, book_author, book_pub, book_count, book_intime, book_type, book_note);
        if (bookService.addNewBook(book)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success","添加成功!"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","添加失败!\n该书已存在！"));
        }
    }

    @PostMapping (value = "/deleteBook", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewBook(String book_isbn) {
        if (bookService.deleteBook(book_isbn)) {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("success","删除成功!数据库已无该isbn编号书"));
        } else {
            return StringUtils.mapToJson(StringUtils.returnToAjaxFormat("failed","删除失败!"));
        }
    }

}
