package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.CreateBookingRequestDto;
import com.example.bookmyshow.dtos.CreateBookingResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @PostMapping
     public CreateBookingResponseDto createBooking(@RequestBody CreateBookingRequestDto createBookingRequestDto){
      return null;
    }

}
