package com.myapp.WishList.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
public class Product {
    private Long codProduct;
    private String name;

}
