package com.itprofessor.bookingh2.repository;

import com.itprofessor.bookingh2.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
