package com.tedu.library.dao;

import com.tedu.library.pojo.ReaderCard;
import com.tedu.library.pojo.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReaderCardDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.tedu.library.dao.ReaderCardDao.";


    public int getIdMatchCount( int reader_id,  String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatchCount", map);
    }

    public ReaderCard findReaderByReaderId(final int reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderByReaderId", reader_id);
    }

    public int resetPassword(final int reader_id, final String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", newPassword);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", map);
    }

    public int addReaderCard( ReaderInfo readerInfo, String password) {
        String username = readerInfo.getName();
        int reader_id = readerInfo.getReaderId();
        Map<String, Object> map = new HashMap<>();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        map.put("reader_id", reader_id);
        map.put("username", username);
        map.put("password", password);
        return sqlSessionTemplate.insert(NAMESPACE + "addReaderCard", map);
    }

    public String getPassword(final int reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", reader_id);
    }

    public int deleteReaderCard(final int reader_id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderCard", reader_id);
    }
}
