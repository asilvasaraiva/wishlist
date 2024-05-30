package com.myapp.WishList.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseException {
    private LocalDateTime timestamp;
    private String message;

}
