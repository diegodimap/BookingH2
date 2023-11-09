package com.itprofessor.bookingh2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.itprofessor.bookingh2.data.DataCheck;
import com.itprofessor.bookingh2.dto.BookingDto;
import com.itprofessor.bookingh2.model.Booking;
import com.itprofessor.bookingh2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class BookingController {


    private final BookingRepository repository;

    @Autowired
    public BookingController( BookingRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/createBooking")
    public int createBooking(@RequestBody BookingDto bookingDto) throws ParseException {
        Booking booking = new Booking();
        booking.setUser_id(bookingDto.getUser_id());
        booking.setProperty_id(bookingDto.getProperty_id());

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate = format.parse(bookingDto.getStart_date());
        Date endDate = format.parse(bookingDto.getEnd_date());

        booking.setStart_date(startDate);
        booking.setEnd_date(endDate);

        //before saving, need to verify if dates are not blocked nor overlap other bookings
        DataCheck dataCheck = new DataCheck();
        System.out.println(startDate);
        System.out.println(endDate);
        String result = dataCheck.getBookings(h2DateFormat(startDate), h2DateFormat(endDate), bookingDto.getProperty_id());
        System.out.println(result);

        //overlaping booking = false
        if(result.length() == 0) {
            repository.save(booking);
        }

        return booking.getId();
    }

    private String h2DateFormat(Date date) {
        return (date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + date.getDate();
    }

    @PutMapping("/updateBooking/{id}")
    public String updateBooking(@PathVariable int id, @RequestBody BookingDto bookingDto) throws ParseException {
        Optional<Booking> bookingDatabase = repository.findById(id);

        if(bookingDatabase.isPresent()) {
            bookingDatabase.get().setProperty_id(bookingDto.getProperty_id());
            bookingDatabase.get().setUser_id(bookingDto.getUser_id());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date startDate = format.parse(bookingDto.getStart_date());
            Date endDate = format.parse(bookingDto.getEnd_date());
            bookingDatabase.get().setStart_date(startDate);
            bookingDatabase.get().setEnd_date(endDate);

            repository.save(bookingDatabase.get());
            return "200";
        }else{
            return "204";
        }
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable int id){
        Optional<Booking> booking = repository.findById(id);

        if(booking.isPresent()){
            repository.deleteById(id);
            return "200";
        }else{
            return "204";
        }
    }

    @GetMapping("/listBookings")
    public String findAllBookings() throws JsonProcessingException {
        Iterable<Booking> bookings = repository.findAll();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(bookings);

        return json;
    }

    @GetMapping("/data")
    public String checkData() throws JsonProcessingException {
        DataCheck dataCheck = new DataCheck();

        String teste = dataCheck.getBookings("2023-11-10", "2023-11-15", 3);

        return teste;
    }

}
