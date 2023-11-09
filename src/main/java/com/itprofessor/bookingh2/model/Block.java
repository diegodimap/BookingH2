package com.itprofessor.bookingh2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class Block {

    @Id
    int id;

    int property_id;

    Date start_date;
    Date end_date;

    public Block() {
    }

    public Block(int id, int property_id, Date start_date, Date end_date) {
        this.id = id;
        this.property_id = property_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
