package com.myapp.WishList.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Document
@Data
@AllArgsConstructor
@Builder
public class Product {
    @NotBlank(message = "Field can't be empty")
    private Long codProduct;
    private String name;
}
