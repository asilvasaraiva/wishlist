package com.myapp.WishList.service;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;

import java.util.Set;

public interface WishService {
    boolean checkProductExist(String email, Long codProduct);
    void removeProduct(String email, Long codProduct);
    void addProductToClientList(RequestDTO req);
    Set<Product> getWishList(String email);
}
