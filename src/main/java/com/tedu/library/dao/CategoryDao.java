package com.tedu.library.dao;

import com.tedu.library.pojo.Category;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao {
    private final static String NAMESPACE = "com.tedu.library.dao.CategoryDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public Category getOne(int classId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getOne", classId);
    }

    public List<Category> getAll(){
        List<Category> result = sqlSessionTemplate.selectList(NAMESPACE+"getAll");
        System.out.println(result);
        return result;
    }
    public int deleteOne(int classId){
        return sqlSessionTemplate.delete(NAMESPACE+"deleteOne",classId);
    }
    public int updateOne(Category category){
        return sqlSessionTemplate.update(NAMESPACE+"updateOne",category);
    }
    public int addOne(Category category){
        return sqlSessionTemplate.insert(NAMESPACE+"addOne",category);
    }

}
