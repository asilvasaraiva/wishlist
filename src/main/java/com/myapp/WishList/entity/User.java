package com.myapp.WishList.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@Builder
public class User {
    @Id
    private String id;
    private String login;
    private Set<Product> wishList;
}
