package com.tuxzx.dal.rowMapper;

import com.tuxzx.domain.TicketInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<TicketInfo> {
    @Override
    public TicketInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setTicketId(resultSet.getInt("ticket_id"));
        ticketInfo.setStuId(resultSet.getString("stu_id"));
        ticketInfo.setBookISBN(resultSet.getString("book_isbn"));
        ticketInfo.setOverDate(resultSet.getInt("over_date"));
        ticketInfo.setFee(resultSet.getFloat("ticket_fee"));
        return ticketInfo;
    }
}
