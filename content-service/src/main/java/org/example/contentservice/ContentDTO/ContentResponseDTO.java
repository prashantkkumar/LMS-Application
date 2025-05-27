package org.example.contentservice.ContentDTO;


import lombok.Data;


@Data

public class ContentResponseDTO {
    private StudentDTO student;
    private CouseDTO course;
    private InstructorDTO instructor;
}
