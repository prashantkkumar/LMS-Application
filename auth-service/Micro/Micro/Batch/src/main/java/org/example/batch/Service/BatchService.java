package org.example.batch.Service;

import org.example.batch.DTO.BatchDTO;
import org.example.batch.DTO.CouseDTO;
import org.example.batch.DTO.InstructorDTO;
import org.example.batch.DTO.StudentDTo;
import org.example.batch.FeignClient.CourseClient;
import org.example.batch.Entity.Batch;
import org.example.batch.FeignClient.InstructorClient;
import org.example.batch.FeignClient.StudentClient;
import org.example.batch.Repository.BatchRepository;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final StudentClient studentClient;
    private final InstructorClient instructorClient;
    private final CourseClient courseClient;

    public BatchService(
            BatchRepository batchRepository,
            StudentClient studentClient,
            InstructorClient instructorClient,
            CourseClient courseClient
    ) {
        this.batchRepository = batchRepository;
        this.studentClient = studentClient;
        this.instructorClient = instructorClient;
        this.courseClient = courseClient;
    }

    public BatchDTO getBatchById(Long id) {
        Batch batch = (Batch) batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        StudentDTo student = studentClient.getStudentById(batch.getStudentId());
        InstructorDTO instructor = instructorClient.getInstructorById(batch.getInstructorId());
        CouseDTO course = courseClient.getCourseById(batch.getCourseId());

        return new BatchDTO(
                batch.getId(),
                batch.getName(),
                student,
                instructor,
                course,
                batch.getSchedules(),
                batch.getAttendances()
        );
    }
}
