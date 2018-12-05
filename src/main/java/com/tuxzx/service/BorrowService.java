package com.tuxzx.service;

import com.tuxzx.dal.BookDao;
import com.tuxzx.dal.BorrowDao;
import com.tuxzx.dal.UserDao;
import com.tuxzx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class BorrowService {

    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    public String getBorrowedBook(String stuId) {
        List<Map<String, Object>> list = borrowDao.getUserBookInfoWithBorrowAndReturn(stuId, BorrowDao.NOTRETURNED);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>借阅号</th><th>书名</th><th>ISBN</th><th>借阅时间</th><th>预计归还时间</th><th>归还状态</th></thead><tbody>");
        if (list == null|| list.size()<=0) {
            stringBuilder.append("暂无数据");
            System.out.println("无数据");
        }
        for (int i=0; i<list.size();i++) {
            Map<String, Object> map = list.get(i);
            stringBuilder.append("<tr>" +
                    "<td>"+map.get("borrow_id")+"</td>"+
                    "<td>"+map.get("book_name")+"</td>"+
                    "<td>"+map.get("book_isbn")+"</td>"+
                    "<td>"+map.get("borrow_date")+"</td>"+
                    "<td>"+map.get("expect_date")+"</td>");
                    if (map.get("return_date")==null) {
                        stringBuilder.append("<td><span class=\"label label-danger\">未归还</span></td>");
                    }else {
                        stringBuilder.append("<td>" + map.get("return_date") + "</td>");
                    }
                    stringBuilder.append("</tr>");
        }
        stringBuilder.append("</tbody></table>");

        return stringBuilder.toString();
    }

    public String getBorrowedBookHistory(String stuId) {
        List<Map<String, Object>> list = borrowDao.getUserBookInfoWithBorrowAndReturn(stuId, BorrowDao.ALL);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>借阅号</th><th>还书号</th><th>书名</th><th>ISBN</th><th>借阅时间</th><th>预计归还时间</th><th>归还时间</th><th>归还状态</th></thead><tbody>");
        if (list == null|| list.size()<=0) {
            stringBuilder.append("暂无数据");
            System.out.println("无数据");
        }
        for (int i=0; i<list.size();i++) {
            Map<String, Object> map = list.get(i);
            stringBuilder.append("<tr>" +
                    "<td>"+map.get("borrow_id")+"</td>");
            if ((int)map.get("return_id")==0) {
                stringBuilder.append("<td><span class=\"label label-default\">null</span></td>");
            }else {
                stringBuilder.append("<td>" + map.get("return_id")+"</td>");
            }
            stringBuilder.append("<td>"+map.get("book_name")+"</td>");
            stringBuilder.append("<td>"+map.get("book_isbn")+"</td>");
            stringBuilder.append("<td>"+map.get("borrow_date")+"</td>");
            stringBuilder.append("<td>"+map.get("expect_date")+"</td>");

            if (map.get("return_date")==null) {
                stringBuilder.append("<td><span class=\"label label-default\">null</span></td>");
                stringBuilder.append("<td><span class=\"label label-danger\">未归还</span></td>");
            }else {
                stringBuilder.append("<td>" + map.get("return_date")+"</td>");
                stringBuilder.append("<td><span class=\"label label-success\">已归还</span></td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</tbody></table>");

        return stringBuilder.toString();
    }

    public String getBorrowedBookHistoryAll() {
        List<Map<String, Object>> list = borrowDao.getAllBookInfoWithBorrowAndReturn();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>借阅号</th><th>还书号</th><th>学生id</th><th>学生姓名</th><th>ISBN</th><th>书名</th><th>借阅时间</th><th>预计归还时间</th><th>归还时间</th><th>归还状态</th></thead><tbody>");
        if (list == null|| list.size()<=0) {
            stringBuilder.append("暂无数据");
            System.out.println("无数据");
        }
        for (int i=0; i<list.size();i++) {
            Map<String, Object> map = list.get(i);
            stringBuilder.append("<tr>" +
                    "<td>"+map.get("borrow_id")+"</td>");
            if ((int)map.get("return_id")==0) {
                stringBuilder.append("<td><span class=\"label label-default\">null</span></td>");
            }else {
                stringBuilder.append("<td>" + map.get("return_id")+"</td>");
            }
            stringBuilder.append("<td>"+map.get("stu_id")+"</td>");
            stringBuilder.append("<td>"+map.get("stu_name")+"</td>");
            stringBuilder.append("<td>"+map.get("book_isbn")+"</td>");
            stringBuilder.append("<td>"+map.get("book_name")+"</td>");
            stringBuilder.append("<td>"+map.get("borrow_date")+"</td>");
            stringBuilder.append("<td>"+map.get("expect_date")+"</td>");

            if (map.get("return_date")==null) {
                stringBuilder.append("<td><span class=\"label label-default\">null</span></td>");
                stringBuilder.append("<td><span class=\"label label-danger\">未归还</span></td>");
            }else {
                stringBuilder.append("<td>" + map.get("return_date")+"</td>");
                stringBuilder.append("<td><span class=\"label label-success\">已归还</span></td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</tbody></table>");

        return stringBuilder.toString();
    }

    public String getBorrowedRanking() {
        List<Map<String, Object>> list = borrowDao.getBookBorrowedCountAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-striped table-hover\"><thead><tr><th>书名</th><th>ISBN</th><th>借阅次数</th></tr></thead><tbody>");
        if (list == null|| list.size()<=0) {
            stringBuilder.append("暂无数据");
            System.out.println("无数据");
        }
        int count = (int) list.get(0).get("count");
        float flag = (float) count / 100f;
        System.out.println("flag:" +flag);
        for (int i=0; i<list.size();i++) {
            Map<String, Object> map = list.get(i);
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>"+ map.get("book_name")+"</td>");
            stringBuilder.append("<td>"+ map.get("book_isbn")+"</td>");
            stringBuilder.append("<td>"+ StringUtils.generateProgressBar((int)map.get("count"), (int)((int)map.get("count")/flag)) +"</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</tbody></table>");

        return stringBuilder.toString();
    }

    public boolean borrowBook(String stu_id, String book_isbn, Date borrow_date, Date expect_date) {
        return borrowDao.toBorrow(stu_id, book_isbn, borrow_date, expect_date);
    }

    public boolean returnBook(String stu_id, int borrow_id, String book_isbn, Date return_date){
        return borrowDao.toReturn(stu_id, borrow_id, book_isbn, return_date);
    }

    public boolean isBookExist(String book_isbn) {
        return bookDao.getBookByISBN(book_isbn)==null?false:true;
    }

    public boolean isReturned(int borrow_id) {
        return borrowDao.isReturned(borrow_id);
    }
    public boolean isStudentExist(String stu_id) {
        return userDao.isUserExist(stu_id);
    }
    public boolean isBorrowExist(int borrow_id) {
        return borrowDao.isBorrowExist(borrow_id);
    }

}
