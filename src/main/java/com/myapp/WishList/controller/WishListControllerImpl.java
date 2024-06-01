package com.myapp.WishList.controller;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;
import com.myapp.WishList.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class WishListControllerImpl implements WishListController{

    @Autowired
    private WishListService wishListService;

    @Operation
    public ResponseEntity<Void> addProductToClient(@RequestBody RequestDTO req){
        wishListService.addProductToClientList(req);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Set<Product>> getWishList(@PathVariable String loginID){
        return ResponseEntity.ok().body(wishListService.getWishList(loginID));
    }

    @Override
    public ResponseEntity<Boolean> checkProductExist(@PathVariable String loginID, @PathVariable Long codProduct ){
        return ResponseEntity.ok().body(wishListService.checkProductExist(loginID,codProduct));
    }

    @Override
    public ResponseEntity<Void> removeProduct(@PathVariable String loginID, @PathVariable Long codProduct ){
        wishListService.removeProduct(loginID,codProduct);
        return ResponseEntity.ok().build();
    }

}
