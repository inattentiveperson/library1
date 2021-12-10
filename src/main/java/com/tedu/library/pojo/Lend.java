package com.tedu.library.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Lend implements Serializable {

    private int ser_num;
    private int book_id;
    private int reader_id;
    private Date lend_date;
    private Date back_date;

    public long getReaderId() {
        return reader_id;
    }

    public void setReaderId(int reader_id) {
        this.reader_id = reader_id;
    }

    public Integer getBookId() {
        return book_id;
    }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public void setSer_num(int ser_num) {
        this.ser_num = ser_num;
    }

    public Date getBackDate() {
        return back_date;
    }

    public void setBackDate(Date back_date) {
        this.back_date = back_date;
    }

    public Date getLendDate() {
        return lend_date;
    }

    public void setLendDate(Date lend_date) {
        this.lend_date = lend_date;
    }

    public int getSer_num() {
        return ser_num;
    }
}
