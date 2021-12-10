package com.tedu.library.vo;

import lombok.Data;
@Data
public class LoginForm {


    private  Integer reader_id;
    private  String password;
    private  String verifyCodeActual;

    public String getVerifyCodeActual() {
        return verifyCodeActual;
    }

    public void setVerifyCodeActual(String verifyCodeActual) {
        this.verifyCodeActual = verifyCodeActual;
    }

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
