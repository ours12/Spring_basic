package com.example.spring_basic.controller;

import com.example.spring_basic.domain.Course;
import com.example.spring_basic.domain.CourseRepository;
import com.example.spring_basic.domain.CourseRequestDto;
import com.example.spring_basic.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    @PostMapping("/api/courses")
    public Course createCourse(@RequestBody CourseRequestDto requestDto){
        // requestDto 는 생성 요청을 의미한다.
        // 강의를 만들기 위해서는 강의제목과 튜터가 필요하니까
        // 그 정보를 가져오는 것이다.

        // 저장하는것은 Dto가 아니라, Course이니, Dto의 정보를 course에 담아야한다.
        // 잠시 뒤에 새로운 생성자를 만든다.
        Course course = new Course(requestDto);

        // JPA를 이용해서 DB에 저장하고, 그 결과를 반환한다.
        return courseRepository.save(course);
    }


    @GetMapping("/api/courses")
    public List<Course> getCourse(){
        return courseRepository.findAll();
    }

    @PutMapping("/api/courses/{id}")
    public Long updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto requestDto){
        return courseService.update(id, requestDto);
    }

    @DeleteMapping("/api/courses/{id}")
    public Long deleteCourse(@PathVariable Long id){
         courseRepository.deleteById(id);
         return id;
    }
}
