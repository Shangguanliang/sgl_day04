package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 实体类：分类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    //如果使用随机生成id形式不能使用自增注解
    @Id
    private String categoryId;
    //类别
    private String categoryName;
    //父id
    private String parentId;
    //是否有子节点
    private Boolean children;
}
