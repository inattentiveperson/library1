package com.tedu.library.pojo;

import lombok.Data;

@Data
public class User {
    private  Integer reader_id;
    private  String password;

    public Integer getReader_id() {
        return reader_id;
    }

    public void setReader_id(Integer reader_id) {
        this.reader_id = reader_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
