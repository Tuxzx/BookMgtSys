package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.ReturnInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnMapper implements RowMapper<ReturnInfo> {
    @Override
    public ReturnInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setReturnId(resultSet.getInt("return_id"));
        returnInfo.setStuId(resultSet.getString("stu_id"));
        returnInfo.setBookISBN(resultSet.getString("book_isbn"));
        returnInfo.setReturnDate(resultSet.getDate("return_date"));
        return null;
    }
}
