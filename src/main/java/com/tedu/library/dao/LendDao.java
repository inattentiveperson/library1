package com.tedu.library.dao;

import com.tedu.library.pojo.Lend;
import com.tedu.library.pojo.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LendDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.tedu.library.dao.LendDao.";

    public int returnBookOne(final int book_id, final int reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.update(NAMESPACE + "returnBookOne", map);
    }

    public int returnBookTwo(final int book_id) {
        return sqlSessionTemplate.update(NAMESPACE + "returnBookTwo", book_id);
    }

    public int lendBookOne(final int book_id, final int reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.insert(NAMESPACE + "lendBookOne", map);
    }

    public int lendBookTwo(final int book_id) {
        return sqlSessionTemplate.update(NAMESPACE + "lendBookTwo", book_id);
    }

    public ArrayList<Lend> lendList() {
        List<Lend> result = sqlSessionTemplate.selectList(NAMESPACE + "lendList");
        return (ArrayList<Lend>) result;
    }

    public ArrayList<Lend> myLendList(Map<String, Object> params) {
        List<Lend> result = sqlSessionTemplate.selectList(NAMESPACE + "myLendList", params);
        return (ArrayList<Lend>) result;
    }
    public List<Lend> findByPager(Map<String, Object> params){
        List<Lend> result = sqlSessionTemplate.selectList(NAMESPACE + "findByPager",params);
        return  result;
    };
    public int count(){
        int total = sqlSessionTemplate.selectOne(NAMESPACE +"count");
        return total;
    }
    public int count1(Integer searchWord){
        int total = sqlSessionTemplate.selectOne(NAMESPACE +"count1",searchWord);
        return total;
    }
    public int deleteLend(final int ser_num) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteLend", ser_num);
    }
}
