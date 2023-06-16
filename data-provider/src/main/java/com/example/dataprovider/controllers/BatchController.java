package com.example.dataprovider.controllers;

import com.example.dataprovider.exceptions.ResourceNotFoundException;
import com.example.dataprovider.models.Batch;
import com.example.dataprovider.models.Course;
import com.example.dataprovider.models.User;
import com.example.dataprovider.repositories.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data-provider/v1/batch")
public class BatchController {
    @Autowired
    private BatchRepository batchRepository;

    @GetMapping
    private List<Batch> getAllBatches(){
        return batchRepository.findAll();
    }

    @PostMapping
    private Batch createBatch(@RequestBody Batch batch){
        return batchRepository.save(batch);
    }

    @GetMapping("/users/{id}")
    private List<User> getUsersByBatchId(@PathVariable String id){
        Optional<Batch> batch = batchRepository.findById(id);
        if(batch.isEmpty()){
            throw new ResourceNotFoundException("Invalid batch id");
        }
        return batch.get().getUsers();
    }

    @GetMapping("/courses/{id}")
    private List<Course> getCoursesByBatchId(@PathVariable String id){
        Optional<Batch> batch = batchRepository.findById(id);
        if(batch.isEmpty()){
            throw new ResourceNotFoundException("Invalid batch id");
        }
        return batch.get().getCourses();
    }
}
