package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.CreateBookingRequestDto;
import com.example.bookmyshow.dtos.CreateBookingResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.exceptions.ShowSeatsNotFoundException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
     public CreateBookingResponseDto createBooking(@RequestBody CreateBookingRequestDto requestDto) throws UserNotFoundException, ShowSeatsNotFoundException {
      CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
      Booking booking = null;


         booking = bookingService.createBooking(
                requestDto.getShowSeatIds(),
                requestDto.getUserId()

        );
        responseDto.setBooking(booking);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        if(booking == null){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

}
