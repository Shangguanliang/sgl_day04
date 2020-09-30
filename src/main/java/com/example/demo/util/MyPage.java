package com.example.demo.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyPage 自定义分页对象
 * @Author geek
 * @Data 2019/4/18 14:47
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class MyPage<T> implements Serializable {

    private static final long serialVersionUID = 6952603824690799336L;

    public Page<T> page;

    private int currentPage; //当前页

    public MyPage(Page page) {
        this.page = page;
        this.currentPage = page.getNumber() + 1;
    }


    /**
     * 获取相近的5个页码
     *
     * @return
     */
    public List<Integer> pageNum() {
        List<Integer> pages = new ArrayList<Integer>();
        // 显示5个相邻的页码
        int first = 1;
        int totalPage = this.page.getTotalPages();
        if (totalPage <= 5) {
            for (int i = 0; i < totalPage; i++) {
                pages.add(first);// 存入页码
                first++;
            }
            return pages;
        }
        int end = currentPage + 2;

        if (currentPage - 2 > 0) {
            first = currentPage - 2;
        }
        if (end > totalPage) {
            end = totalPage; //end=4
        }
        if ((end - first) < 4 && (first + 4) <= totalPage) {// 后不足五个页码的补齐
            end = first + 4;
        }
        if ((end - first) < 4 && (end - 4) >= 1) {// 前不足五个页码的补齐
            first = end - 4;
        }


        int fornum = end - first + 1;
        for (int i = 0; i < fornum; i++) {
            pages.add(first);// 存入页码
            first++;
        }
        return pages;
    }
}