package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseDto {
    private String name;
    private String email;
    private ResponseStatus responseStatus;
}
