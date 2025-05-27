package org.example.batchservice.Service;


import org.example.batchservice.DTO.BatchDTO;
import org.example.batchservice.DTO.CouseDTO;
import org.example.batchservice.DTO.InstructorDTO;
import org.example.batchservice.DTO.StudentDTO;
import org.example.batchservice.Entity.Batch;
import org.example.batchservice.FeignClient.CourseClient;
import org.example.batchservice.FeignClient.InstructorClient;
import org.example.batchservice.FeignClient.StudentClient;
import org.example.batchservice.Repository.BatchRepository;
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

        StudentDTO student = studentClient.getStudentById(batch.getStudentId());
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
