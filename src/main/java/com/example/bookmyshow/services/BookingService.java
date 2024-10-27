package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowSeatsNotFoundException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(UserRepository userRepository, ShowSeatRepository showSeatRepository, PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(List<Long> showSeatIds, Long userId) throws UserNotFoundException, ShowSeatsNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);


        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with Id: " + userId + "is not present");
        }

        User user = optionalUser.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats){
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatsNotFoundException("Showseat with ShowId: " + showSeat.getShow().getId() +
                        "and ShowSeat with SeatId" + showSeat.getSeat().getId()+ "is not available");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setShowSeats(savedShowSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats));

        return booking;
    }
}
