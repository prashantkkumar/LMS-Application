package org.example.batch.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {
    private Long batchId;
    private String batchName;
    private StudentDTo student;
    private InstructorDTO instructor;
    private CouseDTO course;
//    private List<Schedule> schedules;
//    private List<Attendance> attendances;
}
