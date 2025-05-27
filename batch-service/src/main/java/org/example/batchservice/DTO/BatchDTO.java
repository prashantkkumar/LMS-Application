package org.example.batchservice.DTO;

import lombok.*;


@Data
public class BatchDTO {
    private Long batchId;
    private String batchName;
    private StudentDTO student;
    private InstructorDTO instructor;
    private CouseDTO course;
//    private List<Schedule> schedules;
//    private List<Attendance> attendances;
}
