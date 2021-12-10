package com.tedu.library.controller;

import com.tedu.library.pojo.Book;
import com.tedu.library.pojo.Category;
import com.tedu.library.service.BookService;
import com.tedu.library.service.CategoryService;
import com.tedu.library.vo.CateBooks;
import com.tedu.library.vo.Pager;
import com.tedu.library.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:8080",allowCredentials = "true")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/query_book")
    public SysResult queryBookDo(String searchWord,Integer  page,Integer size) {
        Pager<Book> books=new Pager<>();
        if (bookService.matchBook(searchWord)) {
            books = bookService.queryBook(searchWord,page,size);

        } else {
            books = new Pager<Book>();

        }
        return SysResult.success(books);
    }
    @GetMapping("/sortedBooks")
    public List<CateBooks> sortedBooks(Integer page,Integer size){
        List<CateBooks> cateBooks = bookService.getClassifiedBooks(page,size);
        return cateBooks;//
    }


    @GetMapping("/getCBooks")
    public SysResult getCBooks(Integer value,Integer page,Integer size){
     List<CateBooks> cateBooks = sortedBooks(page,size);
        for (CateBooks c:cateBooks
             ) {
            if (c.getClass_id()==value){
              return SysResult.success(c.getData());
            }
        }
        return SysResult.fail();
    }



    @GetMapping ("/getCategories")
    public List<Category> getCategories(){
        return categoryService.getAll();
    }

    @GetMapping("/admin_books")
/*    public List<Book> adminBooks() {
        ArrayList<Book> books = bookService.();
        return books;
    }*/
    public SysResult adminBooks(Pager<Book> pager){
        int page = pager.getPage();
        int size = pager.getSize();
        Pager<Book> pager1= bookService.getAllBooks(page,size);
        return SysResult.success(pager1);
    }

    @DeleteMapping("/delete_book")
    public SysResult deleteBook(Integer id) {
        if( bookService.deleteBook(id))
            return SysResult.success();
        else
            return SysResult.fail();
    }

    @PostMapping("/add_book")
    public SysResult addBook( @RequestBody Book book) {
        if (bookService.addBook(book)){
            return SysResult.success();
        }else
            return SysResult.fail();
    };

    @PutMapping("/update_book")
    public SysResult bookEdit( @RequestBody Book book) {
        if (bookService.updateBook(book)) {
            return SysResult.success();
        } else {
            return SysResult.fail();
        }

    }
    @RequestMapping("/book_detail")
    public Book adminBookDetail(Integer bookId) {
        Book book = bookService.getBook(bookId);
        return book;
    }


}
