package com.tedu.library.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReaderCard implements Serializable {

    private int reader_id;
    private String username;
    private String password;

    public int getReaderId() {
        return reader_id;
    }

    public void setReaderId(int reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
