package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setISBN(resultSet.getString("book_isbn"));
        book.setName(resultSet.getString("book_name"));
        book.setAuthor(resultSet.getString("book_author"));
        book.setPub(resultSet.getString("book_pub"));
        book.setCount(resultSet.getInt("book_count"));
        book.setInTime(resultSet.getDate("book_intime"));
        book.setType(resultSet.getInt("book_type"));
        book.setNote(resultSet.getString("book_note"));
        return book;
    }
}
