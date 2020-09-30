package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类 JPA API
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    List<Category> findByParentId(String parentId);

    List<Category> findByParentIdIn(List<String> ids);
}
