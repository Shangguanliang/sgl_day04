package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * 书本实体
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String bookId;
    //作者
    @NotBlank(message = "作者名不能为空")
    private String writer;
    //作品名称
    @NotBlank(message = "书名不能为空")
    @Length(min = 1,max = 20)
    private String bookName;

    @NotBlank(message = "介绍不能为空")
    private String introduction;//介绍


    private Integer categoryId;


}
