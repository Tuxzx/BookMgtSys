package com.tuxzx.service;


import com.tuxzx.dal.BookDao;
import com.tuxzx.domain.Book;
import com.tuxzx.domain.BookType;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private BookDao bookDao;

    public Map getAllBookTypeWithHtml(){
        Map map = new HashMap();
        List<BookType> typeList = null;
        typeList = bookDao.getAllTypes();
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append(StringUtils.bookTypeHTMLString(0,"全部"));
        for (BookType bookType:typeList) {
            htmlStr.append(StringUtils.bookTypeHTMLString(bookType.getId(), bookType.getName()));
        }
        map.put("status",200);
        map.put("info", htmlStr.toString());
        return map;
    }

    public Map getAllBookByTypeWithHtml(int type) {
        Map map = new HashMap();
        List<Book> bookList = null;
        StringBuilder htmlStr = new StringBuilder();
        if (type==0) {
            bookList = bookDao.getAllBooks();
        }else {
            bookList = bookDao.getBookByType(type);
        }
        for (Book book:bookList) {
            htmlStr.append(StringUtils.bookHTMLString(book.getISBN(), book.getName()));
        }
        map.put("status",200);
        map.put("info", htmlStr.toString());
        return map;
    }

    public Map getBookInfo(String isbn) {
        Book book = bookDao.getBookByISBN(isbn);
        Map map = new HashMap();
        map.put("book_name", book.getName());
        map.put("book_author", book.getName());
        map.put("book_pub", bookDao.getPubInfo(book.getPub()).getName());
        map.put("book_count", book.getCount());
        map.put("book_intime", book.getInTime());
        map.put("book_note", book.getNote());
        return map;
    }
}
