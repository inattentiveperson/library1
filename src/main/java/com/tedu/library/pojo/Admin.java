package com.tedu.library.pojo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class Admin {

    private int admin_id;
    private String password;
    private String username;

    public int getAdminId() {
        return admin_id;
    }

    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
