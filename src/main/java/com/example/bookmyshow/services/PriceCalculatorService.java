package com.example.bookmyshow.services;

import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatType;
import com.example.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository ) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }


    public int calculatePrice(List<ShowSeat> showSeats) {
        int amount = 0;

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(showSeats.get(0).getShow());

        for(ShowSeat showSeat: showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }

}
