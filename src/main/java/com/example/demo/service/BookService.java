package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import com.example.demo.util.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * 书籍 方法接口
 */
public interface BookService {
    /**
     * 添加书籍信息
     * @param book
     */
    void save(Book book);

    /**
     * 删除书籍信息
     * @param id
     */
    void deleteById(String id);

    /**
     * 获取所有数据
     * @return
     */
    List<Book> findAll();

    /**
     * 分页获取所有数据
     * @param pageable
     * @return
     */
    MyPage<Book> findAll(Pageable pageable);

    /**
     * 根据id 查询书籍
     * @param bookId
     * @return
     */
    Book findById(String bookId);

//    Optional<Book> findOptionById(String bookId);

    /**
     * 根据书籍名称 模糊 分页查询
     * @param keyword
     * @param pageable
     * @return
     */
    public MyPage<Book> findByNameLike(String keyword,Pageable pageable);

    void updateBook(BookDTO bookDTO);

}
