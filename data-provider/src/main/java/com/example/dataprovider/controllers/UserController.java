package com.example.dataprovider.controllers;

import com.example.dataprovider.exceptions.ResourceNotFoundException;
import com.example.dataprovider.models.Batch;
import com.example.dataprovider.models.Course;
import com.example.dataprovider.models.Test;
import com.example.dataprovider.models.User;
import com.example.dataprovider.repositories.BatchRepository;
import com.example.dataprovider.repositories.UserRepository;
import com.example.dataprovider.requests.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data-provider/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BatchRepository batchRepository;
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException(String.format("User not found with id = %s", id));
        }
        return user.get();
    }

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest req){
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .rollNum(req.getRollNum())
                .build();
        Optional<Batch> batch = batchRepository.findById(req.getBatchId());
        if(batch.isEmpty()){
            throw new ResourceNotFoundException("Batch not found");
        }
        user.setBatch(batch.get());
        return userRepository.save(user);
    }

    @GetMapping("/courses/{id}")
    public List<Course> getCoursesByUserId(@PathVariable String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        return user.get().getBatch().getCourses();
    }

    @GetMapping("/name/{id}")
    public String getUserNameById(@PathVariable String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        return user.get().getName();
    }
}
