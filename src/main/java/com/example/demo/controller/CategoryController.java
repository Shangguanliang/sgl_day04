package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 */
@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 添加分类信息
     */
    @ResponseBody
    @PostMapping("/add")
    public void save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
    }

    /**
     * 删除分类
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        categoryService.deleteById(id);
    }

    /**
     * 查询分类
     *
     * @param model
     * @return
     */
    @GetMapping("/all")
    public String findAll(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "/book/index";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") String id){
        return categoryService.findById(id);
    }

    @ResponseBody
    @GetMapping("/children/{parentId}")
    public List<Category> findByParentId(@PathVariable("parentId") String parentId){
        return categoryService.findByParentId(parentId);
    }


}


