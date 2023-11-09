package com.itprofessor.bookingh2.controller;

import com.itprofessor.bookingh2.dto.BookingDto;
import com.itprofessor.bookingh2.model.Booking;
import com.itprofessor.bookingh2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController
public class BookingController {


    private final BookingRepository repository;

    @Autowired
    public BookingController( BookingRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/createBooking")
    public int createTask(@RequestBody BookingDto bookingDto) throws ParseException {
        Booking booking = new Booking();
        booking.setUser_id(bookingDto.getUser_id());
        booking.setProperty_id(bookingDto.getProperty_id());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = format.parse(bookingDto.getStart_date());
        Date endDate = format.parse(bookingDto.getEnd_date());

        booking.setStart_date(startDate);
        booking.setEnd_date(endDate);

        repository.save(booking);

        return booking.getId();
    }


}
