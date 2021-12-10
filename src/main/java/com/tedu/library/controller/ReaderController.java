package com.tedu.library.controller;

import com.tedu.library.pojo.AddOne;
import com.tedu.library.pojo.ReaderInfo;
import com.tedu.library.service.LoginService;
import com.tedu.library.service.ReaderCardService;
import com.tedu.library.service.ReaderInfoService;
import com.tedu.library.vo.Pager;
import com.tedu.library.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:8080",allowCredentials = "true")
public class ReaderController {
    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ReaderCardService readerCardService;
/*    @RequestMapping("/getreaderinfo")*/
    public ReaderInfo getReaderInfo(Integer readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }
    @GetMapping("/getReaderInfoList")
    public SysResult getReaderInfoList(Pager<ReaderInfo> pager){
        int page = pager.getPage();
        int size = pager.getSize();
        Pager<ReaderInfo> pager1= readerInfoService.findByPager(page,size);
        return SysResult.success(pager1);
    }
    @DeleteMapping("/reader_delete")
    public SysResult readerDelete(Integer readerId) {
        readerInfoService.deleteReaderInfo(readerId);
        readerCardService.deleteReaderCard(readerId);
        return SysResult.success();
    }

    @GetMapping("/reader_info")
    public SysResult toReaderInfo(Integer readerId) {
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
        Pager pager = new Pager();
        pager.setPage(1);
        pager.setSize(5);
        pager.setTotal(1);
        List<ReaderInfo> list = new ArrayList<ReaderInfo>();
        list.add(readerInfo);
        pager.setRows(list);
        return SysResult.success(pager);
    }
    @RequestMapping("/findReader")
    public SysResult toReaderInfo1(Integer readerId) {
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
        return SysResult.success(readerInfo);
    }

    @RequestMapping("/reader_edit")
    public SysResult readerInfoEditDo(Integer readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        readerInfoService.editReaderInfo(readerInfo);
        readerInfoService.editReaderCard(readerInfo);
        return SysResult.success();
    }
    @PostMapping("/reader_add")
    public SysResult readerInfoAddDo( @RequestBody AddOne addOne) {
        String name= addOne.getName();
        String sex = addOne.getSex();
        String birth=addOne.getBirth();
        String address=addOne.getAddress();
        String phone=addOne.getPhone();
        String passwd=addOne.getPasswd();
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        int readerId = readerInfoService.addReaderInfo(readerInfo);

        readerInfo.setReaderId(readerId);

        if(readerCardService.addReaderCard(readerInfo, passwd))
            return SysResult.success();
        else
            return SysResult.fail();
    }

/*    @RequestMapping("reader_edit_do")
    public void readerInfoEditDoReader(ReaderCard readerCard,String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        readerInfoService.editReaderInfo(readerInfo);
        readerInfoService.editReaderCard(readerInfo);
        ReaderCard readerCardNew = loginService.findReaderCardByReaderId(readerCard.getReaderId());
    }*/


}

