package com.tuxzx.dal;

import com.tuxzx.domain.BorrowInfo;
import com.tuxzx.domain.ReturnInfo;

import java.sql.Date;
import java.util.List;

public interface BorrowDao {

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
     * @param bookId 书籍id
     * @param returnDate 归还日期
     * @return
     */
    boolean toReturn(String stuId, String bookId, Date returnDate);

    /**
     * 开具罚单
     * @param stuId 学生id
     * @param bookISBN 书籍id
     * @param overDate 超时天数
     * @param fee 罚款金额
     */
    boolean toTicket(String stuId, String bookISBN, int overDate, float fee);

}
