package com.tedu.library.service;

import com.tedu.library.dao.BookDao;
import com.tedu.library.dao.CategoryDao;
import com.tedu.library.pojo.Book;
import com.tedu.library.pojo.Category;
import com.tedu.library.vo.CateBooks;
import com.tedu.library.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CategoryDao categoryDao;

    public Pager<Book> queryBook(String searchWord, int page, int size) {
        Map<String, Object> params = new HashMap<String, Object>();
        searchWord="%" + searchWord + "%";
        params.put("searchWord",searchWord);
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<Book> pager = new Pager<Book>();
        List<Book> list = bookDao.queryBook(params);
        pager.setRows(list);
        int total = bookDao.matchBook(searchWord);
        pager.setTotal(total);
        return pager;

    }

/*    public ArrayList<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }*/
    public Pager<Book> getAllBooks(int page, int size){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page-1)*size);
        params.put("size", size);
        Pager<Book> pager = new Pager<Book>();
        List<Book> list = bookDao.findByPager(params);
        pager.setRows(list);
        int total = bookDao.count();
        pager.setTotal(total);
        return pager;
    }

    public List<CateBooks> getClassifiedBooks(int page,int size){
        Map<String,Object> params = new HashMap<>();
        params.put("page",page);
        params.put("size",size);
        List<Category> all = categoryDao.getAll();
        List<CateBooks> list = new ArrayList<>();
        for (Category c:all) {
            params.put("classId",c.getClassId());
            List<Book> classifiedBooks = bookDao.getClassifiedBooks(params);
            CateBooks cateBooks1 = new CateBooks();
            cateBooks1.setClass_id(c.getClassId());
            cateBooks1.setClass_name(c.getClassName());
            Pager<Book> pager = new Pager<>();
            pager.setTotal(bookDao.count2(c.getClassId()));
            pager.setPage(page);
            pager.setSize(size);
            pager.setRows(classifiedBooks);
            cateBooks1.setData(pager);
            list.add(cateBooks1);
        }
        return list;
    }

    public boolean matchBook(String searchWord) {
        return bookDao.matchBook(searchWord) > 0;
    }

    public boolean addBook(Book book) {
        return bookDao.addBook(book) > 0;
    }

    public Book getBook(Integer bookId) {
        return bookDao.getBook(bookId);
    }

    public boolean editBook(Book book) {
        return bookDao.editBook(book) > 0;
    }

    public boolean deleteBook(Integer bookId) {
        return bookDao.deleteBook(bookId) > 0;
    }

    public boolean updateBook(Book book) {return bookDao.editBook(book) > 0;
    }
}
