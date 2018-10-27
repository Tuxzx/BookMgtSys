package com.tuxzx.service;

import com.tuxzx.dal.BookDao;
import com.tuxzx.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public String getNewerBookInfo() {
        List<Book> bookList = bookDao.getNewerBook(20);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>书名</th><th>ISBN</th><th>借阅次数</th></tr></thead><tbody>");
        for (Book book: bookList) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>");
        }
        return stringBuilder.toString();
    }

}
