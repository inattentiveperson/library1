package com.tedu.library.service;

import com.tedu.library.dao.ReaderCardDao;
import com.tedu.library.pojo.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardService {
    @Autowired
    private ReaderCardDao readerCardDao;

    public boolean addReaderCard(ReaderInfo readerInfo, String password){
        return  readerCardDao.addReaderCard(readerInfo,password)>0;
    }
    public boolean updatePassword(int readerId, String password){
        return readerCardDao.resetPassword(readerId,password)>0;
    }

    public boolean deleteReaderCard(int readerId) {
        return readerCardDao.deleteReaderCard(readerId) > 0;
    }
}
