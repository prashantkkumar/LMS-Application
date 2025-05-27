package org.example.contentservice.Service;


import org.example.contentservice.ContentDTO.ContentResponseDTO;
import org.example.contentservice.ContentDTO.CouseDTO;
import org.example.contentservice.ContentDTO.InstructorDTO;
import org.example.contentservice.ContentDTO.StudentDTO;
import org.example.contentservice.FeignClient.CourseClient;
import org.example.contentservice.FeignClient.InstructorClient;
import org.example.contentservice.FeignClient.StudentClient;
import org.springframework.stereotype.Service;


@Service
public class ContentService {
    private final StudentClient studentClient;
    private final CourseClient courseClient;
    private final InstructorClient instructorClient;

    public ContentService(StudentClient studentClient, CourseClient courseClient, InstructorClient instructorClient) {
        this.studentClient = studentClient;
        this.courseClient = courseClient;
        this.instructorClient = instructorClient;
    }

    public ContentResponseDTO getFullContent(Long id) {
        StudentDTO student = studentClient.getStudentById(id);
        CouseDTO course = courseClient.getCourseById(id);
        InstructorDTO instructor = instructorClient.getInstructorById(id);

        return new ContentResponseDTO(student, course, instructor);
    }
}
