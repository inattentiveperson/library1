package com.tedu.library.service;

import com.tedu.library.dao.ReaderInfoDao;
import com.tedu.library.pojo.ReaderInfo;
import com.tedu.library.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReaderInfoService {
    @Autowired
    private ReaderInfoDao readerInfoDao;

    public ArrayList<ReaderInfo> readerInfos() {
        return readerInfoDao.getAllReaderInfo();
    }

    public boolean deleteReaderInfo(int readerId) {
        return readerInfoDao.deleteReaderInfo(readerId) > 0;
    }

    public ReaderInfo getReaderInfo(int readerId) {
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }

    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return readerInfoDao.editReaderInfo(readerInfo) > 0;
    }

    public boolean editReaderCard(ReaderInfo readerInfo) {
        return readerInfoDao.editReaderCard(readerInfo) > 0;
    }

    public int addReaderInfo(ReaderInfo readerInfo) {
        return readerInfoDao.addReaderInfo(readerInfo);
    }

    public Pager<ReaderInfo> findByPager(int page, int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<ReaderInfo> pager = new Pager<ReaderInfo>();
        List<ReaderInfo> list = readerInfoDao.findByPager(params);
        pager.setRows(list);
        int total = readerInfoDao.count();
        pager.setTotal(total);
        return pager;
    }
}
