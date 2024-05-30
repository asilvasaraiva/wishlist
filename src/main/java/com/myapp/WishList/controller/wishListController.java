package com.myapp.WishList.controller;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;
import com.myapp.WishList.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/wishlist")
public class wishListController {

    @Autowired
    private WishService wishService;

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProductToClient(@RequestBody RequestDTO req){
        wishService.addProductToClientList(req);
        return ResponseEntity.ok().build();
    }

}
