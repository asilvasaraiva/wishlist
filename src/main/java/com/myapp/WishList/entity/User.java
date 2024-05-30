package com.myapp.WishList.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Document
@Data
@Builder
public class User {
    @Id
    private String id;
    @NotBlank(message = "Field can't be empty")
    private String login;
    private Set<Product> wishList;
}
