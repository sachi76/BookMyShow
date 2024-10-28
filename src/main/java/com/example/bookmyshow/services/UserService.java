package com.example.bookmyshow.services;

import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            // navigate the user to login page
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);



        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    public ResponseStatus login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            //ask User to sigup first
        }

        User user = optionalUser.get();

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return ResponseStatus.SUCCESS;
        }
        return ResponseStatus.FAILURE;

    }
}
