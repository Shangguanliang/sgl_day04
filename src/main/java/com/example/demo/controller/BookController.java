package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.enums.ExceptionType;
import com.example.demo.exception.GlobalException;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.util.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 书籍控制器
 */
@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    //注入相应注解

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    //显示所有数据并分页查询
    @GetMapping("/index")
    public String showIndex(@PageableDefault(page = 1,size = 3) Pageable pageable, Model model,
                            @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword){
        MyPage<Book> bookPage = new MyPage<>();
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1,pageable.getPageSize());
        //判断传入的值是否是空值，若是空值则查询全部，如果不是则进行模糊分页查询
        if(StringUtils.isNotBlank(keyword)){
            bookPage = bookService.findByNameLike(keyword,pageable);
        }else{
            bookPage = bookService.findAll(pageRequest);
        }
        List<Category> categoryList = categoryService.findByParentId("0");
        model.addAttribute("bookPage",bookPage);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("keyword",keyword);
        return "/book/index";
    }

    //跳转到添加页面
    @GetMapping("/add")
    public String addPage(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "/book/addBook";
    }

    //添加成功后重定向回主页面
    @PostMapping("/add")
    public String saveBook(@Valid Book book)  {
        bookService.save(book);
        return "redirect:/book/index";
    }

    //删除后重定向回主页面
    @ResponseBody
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id){
        bookService.deleteById(id);
        return "/index";
    }

    @ResponseBody
    @GetMapping("/data")
    public List<Book> bookData(){
        return bookService.findAll();
    }

    //跳转到修改页面,并将数据传给修改页面
    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable("id") String id, Model model){
        Book book = bookService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("book",book);
        return "book/updateBook";
    }
    //修改成功后返回主界面
    @PutMapping("/update")
    public String updateBook(@Valid BookDTO bookDTO) throws Exception {
        bookService.updateBook(bookDTO);
        return "redirect:/book/index";
    }

    //若报错就返回错误
    @GetMapping("/exception/{msg}")
    public String exception(@PathVariable String msg){
        if(StringUtils.isEmpty(msg)){
            throw new GlobalException(ExceptionType.BOOK_SAVE_ERROR);
        }else if("null".equals(msg)){
            throw new GlobalException(ExceptionType.BOOK_NOT_FOUND);
        }else{
            throw new GlobalException(ExceptionType.CATEGORY_NOT_FOUND);
        }
    }

}
