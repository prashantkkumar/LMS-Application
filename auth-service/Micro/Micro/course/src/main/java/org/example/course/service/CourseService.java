package org.example.course.service;

import org.example.course.dto.CourseDTO;
import org.example.course.entity.Course;
import org.example.course.mapper.CourseMapper;
import org.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private CourseMapper courseMapper;

        public CourseDTO createCourse(CourseDTO courseDTO) {
            Course course = courseMapper.toEntity(courseDTO);
            Course savedCourse = courseRepository.save(course);
            return courseMapper.toDTO(savedCourse);
        }

        public List<CourseDTO> getAllCourses() {
            List<Course> courses = courseRepository.findAll();
            return courses.stream()
                    .map(courseMapper::toDTO)
                    .collect(Collectors.toList());
        }

        public Optional<CourseDTO> getCourseById(Long id) {
            return courseRepository.findById(id)
                    .map(courseMapper::toDTO);
        }

        public Optional<CourseDTO> updateCourse(Long id, CourseDTO courseDTO) {
            return courseRepository.findById(id).map(existingCourse -> {
                courseMapper.updateEntity(courseDTO, existingCourse);
                Course updatedCourse = courseRepository.save(existingCourse);
                return courseMapper.toDTO(updatedCourse);
            });
        }

        public boolean deleteCourse(Long id) {
            return courseRepository.findById(id).map(course -> {
                courseRepository.delete(course);
                return true;
            }).orElse(false);
        }


    }
