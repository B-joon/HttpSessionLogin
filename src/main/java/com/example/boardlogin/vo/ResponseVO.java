package com.example.boardlogin.vo;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResponseVO<T> {
    HttpStatus status;
    T data;
}
