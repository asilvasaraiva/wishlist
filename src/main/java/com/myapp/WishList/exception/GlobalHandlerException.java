package com.myapp.WishList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Class that intercepts and handle exceptions in requests the API global scope.
 * @author Alexsandro Saraiva
 */

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<StandardResponseException> handleNotFoundException(NotFoundException notFoundException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                notFoundException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public final ResponseEntity<StandardResponseException> handleProductAlreadyExistException(ProductAlreadyExistException productAlreadyExistException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                productAlreadyExistException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(FullWishListException.class)
    public final ResponseEntity<StandardResponseException> handleFullWishListException(FullWishListException fullWishListException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                fullWishListException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
