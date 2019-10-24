package com.mikul.FullStackAttempt.controller;

import com.mikul.FullStackAttempt.dto.Course;
import com.mikul.FullStackAttempt.service.CoursesHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CourseResource {
    @Autowired
    private CoursesHardcodedService courseManagementService;

    @PostMapping("/instructors/{username}/courses")
    public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {

        Course createdCourse = courseManagementService.save(course);

        // Location
        // Get current resource url
        /// {id}
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCourse.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/instructors/{username}/courses")
    public List<Course> getAllCourses(@PathVariable String username) {
        return courseManagementService.findAll();
    }

    @GetMapping("/instructors/{username}/courses/{id}")
    public Course getCourse(@PathVariable String username, @PathVariable long id) {
        return courseManagementService.findById(id);
    }

    @PutMapping("/instructors/{username}/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id, @RequestBody Course course) {
        Course courseUpdated = courseManagementService.save(course);

        return new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/instructors/{username}/courses/{id}")
    public void deleteCourse(@PathVariable String username, @PathVariable long id) {

        courseManagementService.deleteById(id);
    }
}
