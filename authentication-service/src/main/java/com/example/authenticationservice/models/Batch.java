package com.example.authenticationservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_batch")
public class Batch {
    @Id
    private String id;

    @OneToMany(mappedBy = "batch")
    @Column(name = "_users")
    @JsonIgnore
    List<User> users;

    @OneToMany(mappedBy = "batch")
    @JsonIgnore
    List<Course> courses;

    public void addUser(User user){
        users.add(user);
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}
