package com.itprofessor.bookingh2;

import com.itprofessor.bookingh2.model.Booking;
import com.itprofessor.bookingh2.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BookingH2Application {

    public static void main(String[] args) {
        SpringApplication.run(BookingH2Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookingRepository repository){
        return args -> {
            //repository.save(new Booking(0, 1, 1, new LocalDate(), new Date()));
        };
    }

}
