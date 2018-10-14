package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.BookType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTypeMapper implements RowMapper<BookType> {
    @Override
    public BookType mapRow(ResultSet resultSet, int i) throws SQLException {
        BookType bookType = new BookType();
        bookType.setId(resultSet.getInt("type_id"));
        bookType.setName(resultSet.getString("type_name"));
        return bookType;
    }
}
