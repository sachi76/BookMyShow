package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity(name="bookings")
public class Booking extends BaseModel{

   @ManyToOne
   private User user;
   @Enumerated(EnumType.ORDINAL)
   private BookingStatus bookingStatus;

   private int amount;
   @OneToMany
   private List<Payment> payments;
   @OneToMany
   private List<ShowSeat> showSeats;
   private Date bookedAt;
}
