package com.tedu.library.service;

import com.tedu.library.dao.LendDao;
import com.tedu.library.pojo.Lend;
import com.tedu.library.pojo.ReaderInfo;
import com.tedu.library.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LendService {
    @Autowired
    private LendDao lendDao;

    public boolean returnBook(int bookId, int readerId){
        return lendDao.returnBookOne(bookId, readerId)>0 && lendDao.returnBookTwo(bookId)>0;
    }

    public boolean lendBook(int bookId,int readerId){
        return lendDao.lendBookOne(bookId,readerId)>0 && lendDao.lendBookTwo(bookId)>0;
    }

    public Pager<Lend> lendList(int page, int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<Lend> pager = new Pager<Lend>();
        List<Lend> list = lendDao.findByPager(params);
        pager.setRows(list);
        int total = lendDao.count();
        pager.setTotal(total);
        return pager;
    }
    public Pager<Lend> myLendList(int searchWord,int page,int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        params.put("searchWord",searchWord);
        Pager<Lend> pager = new Pager<Lend>();
        List<Lend> list = lendDao.myLendList(params);
        pager.setRows(list);
        int total = lendDao.count1(searchWord);
        pager.setTotal(total);
        return pager;
        }

    public int deleteLend(int serNum) {
        return lendDao.deleteLend(serNum);
    }

}
