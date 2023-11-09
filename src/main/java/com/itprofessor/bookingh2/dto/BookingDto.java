package com.itprofessor.bookingh2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class BookingDto {
    private int property_id;
    private int user_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    private String start_date;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    private String end_date;

    public BookingDto(int property_id, int user_id, String start_date, String end_date) {
        this.property_id = property_id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
