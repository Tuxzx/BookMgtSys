package com.tuxzx.domain;

import java.sql.Date;

public class ReturnInfo {
    private int returnId;
    private String stuId;
    private String bookISBN;
    private Date returnDate;

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookId) {
        this.bookISBN = bookId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
