package com.example.boardlogin.vo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ResponseVO<T> {
    HttpStatus status;
    T data;
}
