package com.example.dataprovider.controllers;

import com.example.dataprovider.exceptions.ResourceNotFoundException;
import com.example.dataprovider.models.Batch;
import com.example.dataprovider.models.Course;
import com.example.dataprovider.models.Test;
import com.example.dataprovider.repositories.BatchRepository;
import com.example.dataprovider.repositories.CourseRepository;
import com.example.dataprovider.requests.CourseCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data-provider/v1/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BatchRepository batchRepository;
    @PostMapping
    public Course createCourse(@RequestBody CourseCreationRequest req){
        Course course = new Course();
        course.setName(req.getName());
        Optional<Batch> batch = batchRepository.findById(req.getBatchId());
        if(batch.isEmpty()){
            throw new ResourceNotFoundException("Batch not found");
        }
        course.setBatch(batch.get());
        return courseRepository.save(course);
    }

    @GetMapping("/tests/{id}")
    public List<Test> getTestsByCourseId(@PathVariable long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()){
            throw new ResourceNotFoundException("Invalid course id");
        }
        return course.get().getTests();
    }
}
