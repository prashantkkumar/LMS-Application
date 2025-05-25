package org.example.studentservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> enrolledBatchIds; // reference to Batch IDs
    private List<Long> enrolledCourseIds; // reference to Course IDs

}

