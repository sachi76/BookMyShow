package com.example.bookmyshow.exceptionHandler;

import com.example.bookmyshow.exceptions.ShowSeatsNotFoundException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException(){

    }

    @ExceptionHandler(ShowSeatsNotFoundException.class)
    public void handleShowSeatNotFoundException(){

    }


}
