package com.tuxzx.dal.impl;

import com.tuxzx.dal.BorrowDao;
import com.tuxzx.dal.rowMapper.BorrowMapper;
import com.tuxzx.dal.rowMapper.ReturnMapper;
import com.tuxzx.dal.rowMapper.TicketMapper;
import com.tuxzx.domain.BorrowInfo;
import com.tuxzx.domain.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BorrowDaoImpl implements BorrowDao {

    private static final String QUERY_BORROW_INFO_ALL = "select * from borrow_info;";
    private static final String QUERY_BORROW_INFO_BY_STUID = "select * from borrow_info where stu_id = ?;";
    private static final String INSERT_BORROW_INFO = "insert into borrow_info (stu_id, book_isbn, borrow_date, expect_date) VALUES (?,?,?,?);";

    private static final String QUERY_RETURN_INFO_ALL = "select * from return_info;";
    private static final String QUERY_RETURN_INFO_BY_STUID = "select * from return_info where stu_id = ?;";
    private static final String INSERT_RETURN_INFO = "insert into return_info (stu_id, book_isbn, return_date) VALUES (?,?,?);";

    private static final String INSERT_TICKET_INFO = "insert into ticket_info (stu_id, book_isbn, over_date, ticket_fee) VALUES (?,?,?,?);";

    // 全部
    private static final String VIEW_QUERY_BORROW_AND_RETURN= "select * from view_borrow_return_info where stu_id = ? order by borrow_date desc";
    // 借出去未还
    private static final String VIEW_QUERY_BORROW_AND_RETURN_ISNULL = "select * from view_borrow_return_info where return_id is null and stu_id = ? order by borrow_date desc";
    // 借出去已还
    private static final String VIEW_QUERY_BORROW_AND_RETURN_ISNOTNULL= "select * from view_borrow_return_info where return_id is not null and stu_id = ? order by borrow_date desc";

    // 借书次数倒序排行
    private static final String VIEW_QUERY_COUNT_BOOK = "select book_name, book_isbn, count(book_isbn) as count from view_borrow_info group by book_isbn order by count desc;";




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

    public List<Map<String, Object>> getUserBookInfoWithBorrowAndReturn(String stuId, int args) {
        String s = null;
        if (args == 0) {
            s = VIEW_QUERY_BORROW_AND_RETURN;
        }else if (args == 1){
            s = VIEW_QUERY_BORROW_AND_RETURN_ISNULL;
        }else if (args == 2){
            s = VIEW_QUERY_BORROW_AND_RETURN_ISNOTNULL;
        }
        final List<Map<String, Object>> list = new ArrayList();
        jdbcTemplate.query(s, new Object[]{stuId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                rs.beforeFirst();
                while (rs.next())
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("borrow_id", rs.getInt("borrow_id"));
                    map.put("return_id", rs.getInt("return_id"));
                    map.put("stu_id", rs.getString("stu_id"));
                    map.put("stu_name", rs.getString("stu_name"));
                    map.put("book_isbn", rs.getString("book_isbn"));
                    map.put("book_name", rs.getString("book_name"));
                    map.put("borrow_date", rs.getDate("borrow_date"));
                    map.put("expect_date", rs.getDate("expect_date"));
                    map.put("return_date", rs.getString("return_date"));
                    list.add(map);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> getBookBorrowedCountAll() {
        final List<Map<String, Object>> list = new ArrayList();
        jdbcTemplate.query(VIEW_QUERY_COUNT_BOOK ,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                rs.beforeFirst();
                while (rs.next())
                    try {
                        Map<String, Object> map = new HashMap<>();
                        map.put("book_name", rs.getString("book_name"));
                        map.put("book_isbn", rs.getString("book_isbn"));
                        map.put("count", rs.getInt("count"));
                        list.add(map);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        });
        return list;
    }
}
