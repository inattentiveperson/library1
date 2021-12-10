package com.tedu.library.vo;

import com.tedu.library.pojo.Book;
import lombok.Data;

import java.util.List;

@Data
public class CateBooks {
    private Integer class_id;
    private String class_name;
    private Pager<Book> data;
}
