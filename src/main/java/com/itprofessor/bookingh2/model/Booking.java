package com.itprofessor.bookingh2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class Booking{
    @Id
    int id;
    int property_id;
    int user_id;
    Date start_date;
    Date end_date;

    public Booking() {
    }

    public Booking(int id, int property_id, int user_id, Date start_date, Date end_date) {
        this.id = id;
        this.property_id = property_id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
