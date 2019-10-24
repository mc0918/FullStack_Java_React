package com.mikul.FullStackAttempt.service;

import com.mikul.FullStackAttempt.dao.CourseRepository;
import com.mikul.FullStackAttempt.dto.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Todo: CHANGE HARDCODED STUFF INTO METHODS TALKING TO DATABASE
    //Todo: Remember to change DB in application.properties when database is created

@Service
public class CoursesHardcodedService {
//    private static List<Course> courses = new ArrayList<>();
//    private static long idCounter = 0;

    @Autowired
    CourseRepository courseRepo;

//    static {
//        courses.add(new Course("in28minutes", "Learn Full stack with Spring Boot and Angular"));
//        courses.add(new Course(++idCounter, "in28minutes", "Learn Full stack with Spring Boot and React"));
//        courses.add(new Course(++idCounter, "in28minutes", "Master Microservices with Spring Boot and Spring Cloud"));
//        courses.add(new Course(++idCounter, "in28minutes", "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
//    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public void deleteById(long id) {

        Course courseFromDb = new Course();

        courseRepo.findById(id).ifPresent(c -> {
            courseFromDb.setUsername(c.getUsername());
            courseFromDb.setDescription(c.getDescription());
            courseFromDb.setId(c.getId());
        });

        courseRepo.delete(courseFromDb);
        System.out.println("deleted course" + courseFromDb.getId());
    }

    public Course findById(long id) {

        Course courseFromDb = new Course();

        courseRepo.findById(id).ifPresent(c -> {
            courseFromDb.setUsername(c.getUsername());
            courseFromDb.setDescription(c.getDescription());
            courseFromDb.setId(c.getId());
        });

        return courseFromDb;
    }

    public Course save(Course course) {
        courseRepo.save(course);
        return course;
    }
}
