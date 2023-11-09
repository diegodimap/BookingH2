package com.itprofessor.bookingh2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockDto {

    private int property_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    private String start_date;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    private String end_date;

    public BlockDto(int property_id, String start_date, String end_date) {
        this.property_id = property_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
