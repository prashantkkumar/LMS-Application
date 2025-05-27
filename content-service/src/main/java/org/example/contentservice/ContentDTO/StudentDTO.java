package org.example.contentservice.ContentDTO;


import lombok.Data;

@Data
public class StudentDTO {
    private long id;

    private long User_id;
    private String firstName;
    private String lastName;

    private String batch;
    private String Course;
    private String Result;
}
