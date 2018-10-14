package com.tuxzx.dal.impl;

import com.tuxzx.dal.BorrowDao;
import com.tuxzx.dal.rowMapper.BorrowMapper;
import com.tuxzx.dal.rowMapper.ReturnMapper;
import com.tuxzx.dal.rowMapper.TicketMapper;
import com.tuxzx.domain.BorrowInfo;
import com.tuxzx.domain.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class BorrowDaoImpl implements BorrowDao {

    private static final String QUERY_BORROW_INFO_ALL = "select * from borrow_info;";
    private static final String QUERY_BORROW_INFO_BY_STUID = "select * from borrow_info where stu_id = ?;";
    private static final String INSERT_BORROW_INFO = "insert into borrow_info (stu_id, book_isbn, borrow_date, expect_date) VALUES (?,?,?,?);";

    private static final String QUERY_RETURN_INFO_ALL = "select * from return_info;";
    private static final String QUERY_RETURN_INFO_BY_STUID = "select * from return_info where stu_id = ?;";
    private static final String INSERT_RETURN_INFO = "insert into return_info (stu_id, book_isbn, return_date) VALUES (?,?,?);";

    private static final String INSERT_TICKET_INFO = "insert into ticket_info (stu_id, book_isbn, over_date, ticket_fee) VALUES (?,?,?,?);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BorrowInfo> getAllBorrowInfo() {
        return jdbcTemplate.query(QUERY_BORROW_INFO_ALL, new BorrowMapper());
    }

    @Override
    public List<BorrowInfo> getUserBorrowInfo(String stuId) {
        return jdbcTemplate.query(QUERY_BORROW_INFO_BY_STUID, new Object[]{stuId}, new BorrowMapper());
    }

    @Override
    public boolean toBorrow(String stuId, String bookISBN, Date borrowDate, Date expectDate) {
        return jdbcTemplate.update(INSERT_BORROW_INFO, new Object[]{stuId, bookISBN, borrowDate, expectDate})>0?true:false;
    }

    @Override
    public List<ReturnInfo> getAllReturnInfo() {
        return jdbcTemplate.query(QUERY_RETURN_INFO_ALL, new ReturnMapper());
    }

    @Override
    public List<ReturnInfo> getUserReturnInfo(String stuId) {
        return jdbcTemplate.query(QUERY_RETURN_INFO_BY_STUID, new Object[]{stuId}, new ReturnMapper());
    }

    @Override
    public boolean toReturn(String stuId, String bookISBN, Date returnDate) {
        return jdbcTemplate.update(INSERT_RETURN_INFO, new Object[]{stuId, bookISBN, returnDate})>0?true:false;
    }

    @Override
    public boolean toTicket(String stuId, String bookISBN, int overDate, float fee) {
        return jdbcTemplate.update(INSERT_TICKET_INFO, new Object[]{stuId, bookISBN, overDate, fee}, new TicketMapper())>0?true:false;
    }
}
