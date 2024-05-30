package com.myapp.WishList.exception;

public class NotFoundException extends CustomException{
    public NotFoundException() {
        super("Resource not Found");
    }
}
