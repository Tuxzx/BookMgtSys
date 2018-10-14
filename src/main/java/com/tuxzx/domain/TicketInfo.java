package com.tuxzx.domain;

public class TicketInfo {
    private int ticketId;
    private String stuId;
    private String bookISBN;
    private int overDate;
    private float fee;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public int getOverDate() {
        return overDate;
    }

    public void setOverDate(int overDate) {
        this.overDate = overDate;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
