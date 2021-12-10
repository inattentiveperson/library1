package com.tedu.library.service;

import com.tedu.library.dao.CategoryDao;
import com.tedu.library.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    public Category getOne(int classId) {
        return categoryDao.getOne(classId);
    }
    public List<Category> getAll(){
        return categoryDao.getAll();
    }
    public int deleteOne(int classId){
        return categoryDao.deleteOne(classId);
    }
    public int updateOne(Category category){
        return categoryDao.updateOne(category);
    }
    public int addOne(Category category){
        return categoryDao.addOne(category);
    }
    /*
        public Category getOne(int classId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getOne", classId);
    }
    public List<Category> getAll(){
        return sqlSessionTemplate.selectList(NAMESPACE+"getAll");
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
     */
}
