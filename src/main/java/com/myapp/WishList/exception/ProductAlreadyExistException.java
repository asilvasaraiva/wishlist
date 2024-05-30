package com.myapp.WishList.exception;

public class ProductAlreadyExistException extends CustomException{
    public ProductAlreadyExistException() {
        super("Product already added to wishlist ");
    }
}
