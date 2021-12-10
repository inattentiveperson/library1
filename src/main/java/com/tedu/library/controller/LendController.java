package com.tedu.library.controller;

import com.tedu.library.pojo.Lend;
import com.tedu.library.pojo.ReaderCard;
import com.tedu.library.pojo.ReaderInfo;
import com.tedu.library.service.BookService;
import com.tedu.library.service.LendService;
import com.tedu.library.vo.Pager;
import com.tedu.library.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:8080",allowCredentials = "true")
public class LendController {
    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;


    @RequestMapping("/reader_books")
    public SysResult readerBooks(Integer searchWord,Integer  page,Integer size) {
        Pager<Lend> myAllLendList = lendService.myLendList(searchWord,page,size);
        ArrayList<Lend> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList.getRows()) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend);
            }
        }
        int size1 = myLendList.size();
        myAllLendList.setRows(myLendList);
        myAllLendList.setTotal(size1);
        return SysResult.success(myAllLendList);
    }

/*    @RequestMapping("/reader_books")
    public List<Lend> readerBooks(Integer readerId) {
        ArrayList<Lend> myAllLendList = lendService.myLendList(readerId);
        ArrayList<Lend> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend);
            }
        }
        return myLendList;
    }*/

    @RequestMapping("/lend_list")
    public SysResult lendList( Pager<Lend> pager) {
        int page = pager.getPage();
        int size = pager.getSize();
        Pager<Lend> pager1= lendService.lendList(page,size);
        return SysResult.success(pager1);
    }


    @RequestMapping("/lend_list1")
    public SysResult readerBooks1(Integer searchWord,Integer  page,Integer size,HttpServletRequest request) {
        Object reader=request.getSession().getAttribute("id");
        searchWord=Integer.parseInt(reader.toString());
        Pager<Lend> myAllLendList = lendService.myLendList(searchWord,page,size);
        ArrayList<Lend> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList.getRows()) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend);
            }
        }
        int size1 = myLendList.size();
        myAllLendList.setRows(myLendList);
        myAllLendList.setTotal(size1);
        return SysResult.success(myAllLendList);
    }



    @RequestMapping("/mylend")
    public SysResult myLend(Integer searchWord,Integer  page,Integer size) {
        Pager<Lend> lendList = lendService.myLendList(searchWord,page,size);
        return SysResult.success(lendList);
    }

    @RequestMapping("/delete_lend")
    public boolean deleteLend(Integer id) {
        if(lendService.deleteLend(id)>0)
            return true;
        else
            return false;

    }

    @RequestMapping("/lend_book")
    public SysResult lendBook(Integer id,HttpServletRequest request){
        Object reader=request.getSession().getAttribute("id");
        int readerId=Integer.parseInt(reader.toString());
        if(lendService.lendBook(id, readerId))
        return SysResult.success();
        else
            return SysResult.fail();
    }
    public ModelAndView bookLend(Integer bookId ,Integer readerId) {
        lendService.lendBook(bookId, readerId);
        return new ModelAndView("redirect:/reader_books?readerId="+readerId);
    }

    @RequestMapping("/return_book")
    public SysResult returnBook(Integer id,HttpServletRequest request){
        Object reader=request.getSession().getAttribute("id");
        int readerId=Integer.parseInt(reader.toString());
        if(lendService.returnBook(id, readerId))
            return SysResult.success();
        else
            return SysResult.fail();
    }
    public ModelAndView bookReturn(Integer bookId ,Integer readerId) {
        lendService.returnBook(bookId, readerId);
        return new ModelAndView("redirect:/reader_books?readerId="+readerId);
    }
}
