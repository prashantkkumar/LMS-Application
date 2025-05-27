package org.example.course.mapper;

import org.example.course.dto.CourseDTO;
import org.example.course.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course) {
        if (course == null) return null;

        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .durationInHours(course.getDurationInHours())
                .build();
    }

    public Course toEntity(CourseDTO dto) {
        if (dto == null) return null;

        return Course.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .durationInHours(dto.getDurationInHours())
                .build();
    }

    public void updateEntity(CourseDTO dto, Course entity) {
        if (dto == null || entity == null) return;

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDurationInHours(dto.getDurationInHours());
    }
}
