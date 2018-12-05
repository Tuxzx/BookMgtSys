package com.tuxzx.dal;

import com.tuxzx.domain.BorrowInfo;
import com.tuxzx.domain.ReturnInfo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface BorrowDao {
    public static final int ALL = 0;
    public static final int NOTRETURNED = 1;
    public static final int RETURNED = 2;

    /**
     * 借阅是否存在
     * @param borrowId
     * @return
     */
    public boolean isBorrowExist(int borrowId);

    /**
     * 获取所有借书信息
     * @return
     */
    List<BorrowInfo> getAllBorrowInfo();

    /**
     * 获取用户借书信息
     * @param stuId 学生id
     * @return
     */
    List<BorrowInfo> getUserBorrowInfo(String stuId);

    /**
     * 借书
     * @param stuId 学生id
     * @param bookISBN 书籍id
     * @param borrowDate 借书日期
     * @param expectDate 预计归还日期
     * @return
     */
    boolean toBorrow(String stuId, String bookISBN, Date borrowDate, Date expectDate);


    /**
     * 获取所有还书信息
     * @return
     */
    List<ReturnInfo> getAllReturnInfo();

    /**
     * 获取学生还书信息
     * @param stuId 学生id
     * @return
     */
    List<ReturnInfo> getUserReturnInfo(String stuId);

    /**
     * 还书
     * @param stuId 学生id
     * @param borrow_id 学生id
     * @param bookIsbn 书籍id
     * @param returnDate 归还日期
     * @return
     */
    boolean toReturn(String stuId, int borrow_id, String bookIsbn, Date returnDate);

    /**
     * 是否归还
     * @param borrow_id
     * @return
     */
    boolean isReturned(int borrow_id);

    /**
     * 开具罚单
     * @param stuId 学生id
     * @param bookISBN 书籍id
     * @param overDate 超时天数
     * @param fee 罚款金额
     */
    boolean toTicket(String stuId, String bookISBN, int overDate, float fee);

    /**
     * 获取借还信息from借阅表和还书表的联合视图中
     * @param stuId
     * @param args 0 所有的； 1 已借未还；2 已借已还
     * @return 一组map对象
     */
    List<Map<String, Object>> getUserBookInfoWithBorrowAndReturn(String stuId, int args);

    /**
     * 获取所有图书的借阅和归还信息
     * from视图
     * @return
     */
    List<Map<String, Object>> getAllBookInfoWithBorrowAndReturn();

    /**
     * 获取所有书借阅的次数
     * @return
     */
    List<Map<String, Object>> getBookBorrowedCountAll();
}
