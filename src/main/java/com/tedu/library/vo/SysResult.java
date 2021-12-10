package com.tedu.library.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    private Integer status;//200成功201失败
    private String msg;//提示信息
    private Object data;//返回的数据
    public static SysResult fail(){
        return new SysResult(201,"服务器处理失败",null);
    }
    public static SysResult success(){
        return new SysResult(200,"服务器处理成功",null);
    }
    public static SysResult success(Object data){
        return new SysResult(200,"服务器处理成功",data);
    }
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }
    public static SysResult fail(String msg,Object data){
        return new SysResult(201,msg,data);
    }

}
