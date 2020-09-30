package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.exception.GlobalException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.util.BeanNullUtil;
import com.example.demo.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * 添加分类
     * @param categoryDTO 前端传输对象
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        categoryDTO.setChildren(false);
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category, BeanNullUtil.getNullPropertyNames(categoryDTO));
        category.setCategoryId(IDUtil.uuid());
        //若传回来的分类对象是子节点，就给父节点设置children属性
        if(!StringUtils.equals(categoryDTO.getParentId(),"0")){
            //获得传回对象的父id并赋值
            Category parentCategory = findById(categoryDTO.getParentId());
            //若赋值的对象中具有子节点，则退出该判断
            if(!parentCategory.getChildren()){
                parentCategory.setChildren(true);
                categoryRepository.save(parentCategory);
            }

        }
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(String id) {
        Category category = findById(id);
        categoryRepository.deleteById(id);
        //删除后判断父节点时候还有子节点，若没有，设置children属性为false
        if(!StringUtils.equals(category.getParentId(),"0")){
            List<Category> parentCategories = findByParentId(category.getParentId());
            if(CollectionUtils.isEmpty(parentCategories)){
                Category parentCategory = findById(category.getParentId());
                parentCategory.setChildren(false);
                categoryRepository.save(parentCategory);
            }
        }
        //删除一个节点下的所有子节点
        //1.创建一个分类id集合，将当前分类id作为初始元素
        List<String> ids = Arrays.asList(id);
        while (true){
            //根据父id in 查询 是否有子节点
            List<Category> categoryList = categoryRepository.findByParentIdIn(ids);
            //若为空，则跳出循环
            if(CollectionUtils.isEmpty(categoryList)){
                return;
            }
            //若有子节点，将子节点的id赋值给ids 进行下一轮的循环 并批量删除该子分类
            ids = categoryList.stream().map(Category::getCategoryId).collect(Collectors.toList());
            categoryRepository.deleteAll(categoryList);//批量删除
        }

    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("该分类不存在"));
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findByParentId(String parentId) {
        return categoryRepository.findByParentId(parentId);
    }
}
