package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.BorrowInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowMapper implements RowMapper<BorrowInfo> {
    @Override
    public BorrowInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        BorrowInfo borrowInfo = new BorrowInfo();
        borrowInfo.setBorrowId(resultSet.getInt("borrow_id"));
        borrowInfo.setStuId(resultSet.getString("stu_id"));
        borrowInfo.setBookISBN(resultSet.getString("book_isbn"));
        borrowInfo.setBorrowDate(resultSet.getDate("borrow_date"));
        borrowInfo.setExpectDate(resultSet.getDate("expect_date"));
        return borrowInfo;
    }
}
