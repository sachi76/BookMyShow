package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.LoginRequestDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.dtos.UserSignUpRequestDto;
import com.example.bookmyshow.dtos.UserSignUpResponseDto;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserSignUpResponseDto signUp(@RequestBody  UserSignUpRequestDto userSignUpRequestDto) {
        User user = userService.signUp(
                userSignUpRequestDto.getName(),
                userSignUpRequestDto.getEmail(),
                userSignUpRequestDto.getPassword()
        );
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        userSignUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        userSignUpResponseDto.setName(user.getName());
        userSignUpResponseDto.setEmail(user.getEmail());
            return userSignUpResponseDto;
    }

    @GetMapping("/login")
    public ResponseStatus logIn(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

    }
}
