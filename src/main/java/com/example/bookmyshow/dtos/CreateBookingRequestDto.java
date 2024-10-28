package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private List<Long> showSeatIds;
    private Long userId;
}
