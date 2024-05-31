package com.myapp.WishList.utils;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;
import com.myapp.WishList.entity.User;

import java.util.HashSet;
import java.util.Set;

public class DataFactory {

    public static User buildUser(){
        return User.builder()
                .login("Client")
                .wishList(buildWishList())
                .build();
    }

    public static Set<Product> buildWishList(){
        Set<Product> myList = new HashSet<>();
        myList.add(buildProduct(1L));
        myList.add(buildProduct(2L));
        myList.add(buildProduct(3L));
        myList.add(buildProduct(4L));
        return myList;
    }

    public static Set<Product> getBachOfProducts(int quantity){
        Set<Product> bachOfProducts = new HashSet<>();

        for(long i=1; i<=quantity;i++){
            bachOfProducts.add(buildProduct(i));
        }

        return bachOfProducts;
    }

    public static Product buildProduct(Long codProd){
        return Product.builder()
                .codProduct(codProd)
                .name("Produto"+codProd)
                .build();
    }

    public static RequestDTO buildRequest(String loginID,Product p){
         RequestDTO req = new RequestDTO();
         req.setLogin(loginID);
         req.setProduct(p);
         return req;
    }

}
