package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

import java.util.List;

/**
 * 分类 接口
 */
public interface CategoryService {
    /**
     * 显示所有分类
     */
    List<Category> findAll();
    /**
     * 添加分类
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 删除分类
     */
    void deleteById(String id);

    /**
     * 根据id查询分类
     */
    Category findById(String id);

    /**
     * 修改分类
     */
    void update(Category category);

    List<Category> findByParentId(String parentId);




}
