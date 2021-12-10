package com.tedu.library.service;

import com.tedu.library.dao.AdminDao;
import com.tedu.library.dao.ReaderCardDao;
import com.tedu.library.pojo.ReaderCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private ReaderCardDao readerCardDao;
    @Autowired
    private AdminDao adminDao;

    public String hasMatchReader(Integer id,String passwd) {

        passwd= DigestUtils.md5DigestAsHex(passwd.getBytes());

        if(readerCardDao.getIdMatchCount(id, passwd)==0){
            return null;
        }
        String token= UUID.randomUUID().toString().replace("-","");
        return token;
    }
    public String hasMatchAdmin(Integer id,String passwd) {

        passwd= DigestUtils.md5DigestAsHex(passwd.getBytes());

        if(adminDao.getMatchCount(id, passwd)==0){
            return null;
        }
        String token= UUID.randomUUID().toString().replace("-","");
        return token;
    }
/*    public boolean hasMatchReader(int readerId,String password){
        return  readerCardDao.getIdMatchCount(readerId, password)>0;
    }*/

    public String getAdminUsername(int adminId) {
        return adminDao.getUsername(adminId);
    }

    public ReaderCard findReaderCardByReaderId(int readerId){
        return readerCardDao.findReaderByReaderId(readerId);
    }

/*
    public boolean hasMatchAdmin(int adminId,String password){
        return adminDao.getMatchCount(adminId, password) == 1;
    }
*/

    public boolean adminRePassword(int adminId, String newPassword){
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        return adminDao.resetPassword(adminId,newPassword)>0;
    }
    public String getAdminPassword(int adminId){
        return adminDao.getPassword(adminId);
    }

    public boolean readerRePassword(int readerId, String newPassword) {
        newPassword=DigestUtils.md5DigestAsHex(newPassword.getBytes());
        return readerCardDao.resetPassword(readerId, newPassword) > 0;
    }

    public String getReaderPassword(int readerId) {
        return readerCardDao.getPassword(readerId);
    }


}
