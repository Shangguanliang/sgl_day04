package com.example.demo.service.impl;

import com.example.demo.dto.BookDTO;
import com.example.demo.enums.ExceptionType;
import com.example.demo.exception.GlobalException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.util.BeanNullUtil;
import com.example.demo.util.IDUtil;
import com.example.demo.util.MyPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书籍 实现类
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    /**
     * 添加书籍信息
     */
    public void save(Book book) {
        //判断此书信息是否在数据库中存在且是否有空字符串
        if (StringUtils.isBlank(book.getBookId())) {
            book.setBookId(IDUtil.uuid());
        }
        //添加
        Book result = bookRepository.save(book);
        //如果添加的数据为空抛出异常
        if (result == null) {
            throw new GlobalException(ExceptionType.BOOK_SAVE_ERROR);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }


    /**
     * 查询所有书籍
     *
     * @return
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 分页查询所有书籍
     *
     * @return
     */
    @Override
    public MyPage<Book> findAll(Pageable pageable) {
        return new MyPage<>(bookRepository.findAll(pageable));
    }

    /**
     * 根据书籍id进行查找
     *
     * @param bookId 书籍id
     * @return
     */
    public Book findById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new GlobalException(ExceptionType.BOOK_NOT_FOUND));
    }

    /*public Optional<Book> findOptionById(String bookId){
        return bookRepository.findById(bookId);
    }*/

    /**
     * 根据书籍名称 模糊 分页查询
     *
     * @param keyword  关键字（书籍名称）
     * @param pageable 分页信息对象
     * @return
     */
    @Override
    public MyPage<Book> findByNameLike(String keyword, Pageable pageable) {
        return new MyPage<>(bookRepository.findByBookNameLike("%" + keyword + "%", pageable));
    }


    /**
     * 修改书籍
     * @param bookDTO 前端传输书籍对象
     */
    @Override
    public void updateBook(BookDTO bookDTO) {
        //判断前端传回的数据是否有id
        if(StringUtils.isBlank(bookDTO.getBookId())){
            throw new GlobalException(ExceptionType.BOOK_UPDATE_ERROR);
        }
        //通过id从数据库中取值
        Book book = findById(bookDTO.getBookId());
        BeanUtils.copyProperties(bookDTO,book, BeanNullUtil.getNullPropertyNames(bookDTO));

        bookRepository.save(book);
    }


}
