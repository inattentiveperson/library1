package com.tedu.library.dao;

import com.tedu.library.pojo.Book;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDao {

    private final static String NAMESPACE = "com.tedu.library.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public int matchBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        return sqlSessionTemplate.selectOne(NAMESPACE + "matchBook", search);
    }
    public ArrayList<Book> getClassifiedBooks(Map<String, Object> params){
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getClassifiedBooks",params);
        return (ArrayList<Book>) result;
    }

    public ArrayList<Book> queryBook(Map<String, Object> params) {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "queryBook", params);
        return (ArrayList<Book>) result;
    }

/*    public ArrayList<Book> getAllBooks() {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllBooks");
        return (ArrayList<Book>) result;
    }*/
    public List<Book> findByPager(Map<String, Object> params){
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "findByPager",params);
        return  result;
    };
    public int addBook(final Book book) {
        return sqlSessionTemplate.insert(NAMESPACE + "addBook", book);
    }

    public Book getBook(final int bookId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getBook", bookId);
    }

    public int editBook(final Book book) {
        return sqlSessionTemplate.update(NAMESPACE + "editBook", book);
    }

    public int deleteBook(final int bookId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteBook", bookId);
    }
    public int count(){
        int total = sqlSessionTemplate.selectOne(NAMESPACE +"count");
        return total;
    }
    public int count1(){
        int total = sqlSessionTemplate.selectOne(NAMESPACE +"count1");
        return total;
    }
    public int count2(int classId){
        int total = sqlSessionTemplate.selectOne(NAMESPACE +"count2",classId);
        return total;
    }


}
