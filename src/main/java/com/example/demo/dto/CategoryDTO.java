package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String categoryId;
    //类别
    private String categoryName;
    //父id
    private String parentId;
    //是否有子节点
    private Boolean children;
}
