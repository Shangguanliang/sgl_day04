package com.example.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BookDTO {

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
}
