package org.example.contentservice.ContentDTO;


import lombok.Data;

@Data
public class ContentDTO {
    private Long id;

    private Long CourseId;
    private String title;
    private String url;
    private String type;
    private Long StudentId;
    private Long InstructorId;
}
