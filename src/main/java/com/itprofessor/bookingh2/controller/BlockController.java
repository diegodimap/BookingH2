package com.itprofessor.bookingh2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.itprofessor.bookingh2.dto.BlockDto;
import com.itprofessor.bookingh2.dto.BookingDto;
import com.itprofessor.bookingh2.model.Block;
import com.itprofessor.bookingh2.model.Booking;
import com.itprofessor.bookingh2.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class BlockController {


    private final BlockRepository repository;

    @Autowired
    public BlockController(BlockRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/createBlock")
    public int createBooking(@RequestBody BlockDto blockDto) throws ParseException {
        Block block = new Block();
        block.setProperty_id(blockDto.getProperty_id());

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate = format.parse(blockDto.getStart_date());
        Date endDate = format.parse(blockDto.getEnd_date());

        block.setStart_date(startDate);
        block.setEnd_date(endDate);

        repository.save(block);

        return block.getId();
    }

    @PutMapping("/updateBlock/{id}")
    public String updateBlock(@PathVariable int id, @RequestBody BlockDto block) throws ParseException {
        Optional<Block> blockDatabase = repository.findById(id);

        if(blockDatabase.isPresent()) {
            blockDatabase.get().setProperty_id(block.getProperty_id());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date startDate = format.parse(block.getStart_date());
            Date endDate = format.parse(block.getEnd_date());
            blockDatabase.get().setStart_date(startDate);
            blockDatabase.get().setEnd_date(endDate);

            repository.save(blockDatabase.get());
            return "200";
        }else{
            return "204";
        }
    }

    @DeleteMapping("/deleteBlock/{id}")
    public String deleteBlock(@PathVariable int id){
        Optional<Block> block = repository.findById(id);

        if(block.isPresent()){
            repository.deleteById(id);
            return "200";
        }else{
            return "204";
        }
    }

    @GetMapping("/listBlocks")
    public String findAllBlocks() throws JsonProcessingException {
        Iterable<Block> blocks = repository.findAll();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(blocks);

        return json;
    }

}
