package com.myapp.WishList.exception;

public class FullWishListException extends CustomException{
    public FullWishListException() {
        super("Maximum limit of products ");
    }
}
