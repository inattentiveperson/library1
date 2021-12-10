package com.tedu.library.controller;


import com.google.code.kaptcha.Constants;
import com.tedu.library.service.LoginService;
import com.tedu.library.util.CodeUtil;
import com.tedu.library.vo.LoginForm;
import com.tedu.library.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
/*

    @RequestMapping(value = {"/", "/login"})
    public ModelAndView toLogin() {
        return new ModelAndView("redirect:/login");
    }
*/

    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @PostMapping("/loginCheck")/*, method = RequestMethod.POST*/
    public SysResult loginCheck(@RequestBody LoginForm loginForm, HttpServletRequest request) {
/*        boolean isReader = loginService.hasMatchReader(id, passwd);
        boolean isAdmin = loginService.hasMatchAdmin(id, passwd);
        HashMap<String, String> res = new HashMap<>();
        if (isAdmin) {
            Admin admin = new Admin();
            admin.setAdminId(id);
            admin.setPassword(passwd);
            String username = loginService.getAdminUsername(id);
            admin.setUsername(username);
            res.put("stateCode", "1");
            res.put("msg", "管理员登陆成功！");
        } else if (isReader) {
            ReaderCard readerCard = loginService.findReaderCardByReaderId(id);
            res.put("stateCode", "2");
            res.put("msg", "读者登陆成功！");
        } else {
            res.put("stateCode", "0");
            res.put("msg", "账号或密码错误！");
        }
        return res;*/
        Integer id = loginForm.getReader_id();
        String passwd = loginForm.getPassword();
        String verifyCodeActual = loginForm.getVerifyCodeActual();
        String token = loginService.hasMatchReader(id,passwd);
        String token1 = loginService.hasMatchAdmin(id,passwd);
        HttpSession session = request.getSession();
// 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
// 遍历enumeration
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("this is :" + session.toString() + "--> ");
            System.out.println(name + " : " + value);
        }
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);//com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY
        System.out.println(request.getSession().toString()+verifyCodeExpected);
        request.getSession().setAttribute("id",id);
        if (StringUtils.hasLength(token) & CodeUtil.checkVerifyCode(verifyCodeActual,request) ) {
            return SysResult.success("用户你好",token);
        } else if(StringUtils.hasLength(token1) & CodeUtil.checkVerifyCode(verifyCodeActual,request)){
            return SysResult.success("管理员你好",token1);
        }else {
                return SysResult.fail();
        }
    }

    @GetMapping("/admin_repasswd_do")
    public SysResult reAdminPasswdDo(String oldPasswd, String newPasswd, String reNewPasswd,HttpServletRequest request) {
        Object reader=request.getSession().getAttribute("id");
        int readerId=Integer.parseInt(reader.toString());
        String password = loginService.getAdminPassword(readerId);
        oldPasswd= DigestUtils.md5DigestAsHex(oldPasswd.getBytes());
        if (password.equals(oldPasswd)) {
            if (loginService.adminRePassword(readerId, newPasswd)) {
                return SysResult.success();
            } else {
                return SysResult.fail();
            }
        } else {
            return SysResult.fail("旧密码错误",null);
        }
    }

    @GetMapping("/reader_repasswd_do")
    public SysResult reReaderPasswdDo(String oldPasswd, String newPasswd, String reNewPasswd,HttpServletRequest request) { //Integer readerId,
        Object reader=request.getSession().getAttribute("id");
        int readerId=Integer.parseInt(reader.toString());
        String password = loginService.getReaderPassword(readerId);
        oldPasswd= DigestUtils.md5DigestAsHex(oldPasswd.getBytes());
        if (password.equals(oldPasswd)) {
            if (loginService.readerRePassword(readerId, newPasswd)) {
                return SysResult.success();
            } else {
                return SysResult.fail();
            }
        } else {
            return SysResult.fail("旧密码错误",null);
        }
    }



    //配置404页面
/*
    @RequestMapping("*")
    public String notFind() {
        return "404";
    }

*/


}
