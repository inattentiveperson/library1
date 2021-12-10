package com.tedu.library.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ReaderInfo implements Serializable {

    private Integer reader_id;
    private String name;
    private String sex;
    private Date birth;
    private String address;
    private String phone;

    public int getReaderId() {
        return reader_id;
    }

    public void setReaderId(int reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
