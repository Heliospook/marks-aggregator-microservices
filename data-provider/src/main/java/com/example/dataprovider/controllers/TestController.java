package com.example.dataprovider.controllers;

import com.example.dataprovider.exceptions.ResourceNotFoundException;
import com.example.dataprovider.models.Course;
import com.example.dataprovider.models.Test;
import com.example.dataprovider.repositories.CourseRepository;
import com.example.dataprovider.repositories.TestRepository;
import com.example.dataprovider.requests.TestCreationRequest;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/data-provider/v1/test")
public class TestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Test createTest(@RequestBody TestCreationRequest req){
        Test test = Test.builder()
                .name(req.getName())
                .fullScore(req.getFullScore())
                .build();

        Optional<Course> course = courseRepository.findById(req.getCourseId());
        if(course.isEmpty()){
            throw new ResourceNotFoundException("Course id invalid");
        }
        test.setCourse(course.get());
        return testRepository.save(test);
    }

    @GetMapping("/name/{id}")
    public String getTestNameById(@PathVariable long id){
        Optional<Test> test = testRepository.findById(id);
        if(test.isEmpty()){
            throw new ResourceNotFoundException("Test not found");
        }
        return test.get().getName();
    }
}
